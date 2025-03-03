package models;

import java.util.Random;

public class Combat {
    private Pokemon playerPokemon;
    private Pokemon opponentPokemon;
    private Random random;

    public Combat(Pokemon playerPokemon, Pokemon opponentPokemon) {
        this.playerPokemon = playerPokemon;
        this.opponentPokemon = opponentPokemon;
        this.random = new Random();
    }

    public void startBattle() {
        System.out.println(
                "Battle starts between " + playerPokemon.getName() + " and " + opponentPokemon.getName() + "!");

        while (!playerPokemon.isFainted() && !opponentPokemon.isFainted()) {
            takeTurn();
        }

        if (playerPokemon.isFainted()) {
            System.out.println(playerPokemon.getName() + " fainted! " + opponentPokemon.getName() + " wins!");
        } else {
            System.out.println(opponentPokemon.getName() + " fainted! " + playerPokemon.getName() + " wins!");
        }
    }

    private void takeTurn() {
        // Apply status effects at the start of the turn
        playerPokemon.applyStatusEffect();
        opponentPokemon.applyStatusEffect();

        if (playerPokemon.isFainted() || opponentPokemon.isFainted()) return;

        // Determine attack order
        Pokemon first, second;
        if (playerPokemon.getSpeed() > opponentPokemon.getSpeed() ||
                (playerPokemon.getSpeed() == opponentPokemon.getSpeed() && random.nextBoolean())) {
            first = playerPokemon;
            second = opponentPokemon;
        } else {
            first = opponentPokemon;
            second = playerPokemon;
        }

        // First Pokémon attacks
        if (first.getStatusEffect() == null || !first.getStatusEffect().preventsAttack()) {
            first.attack(second, first.getMoves().get(0)); // Uses first move by default
        } else {
            System.out.println(first.getName() + " is unable to attack due to status effect!");
        }

        if (second.isFainted()) return;

        // Second Pokémon attacks
        if (second.getStatusEffect() == null || !second.getStatusEffect().preventsAttack()) {
            second.attack(first, second.getMoves().get(0));
        } else {
            System.out.println(second.getName() + " is unable to attack due to status effect!");
        }
    }
}
