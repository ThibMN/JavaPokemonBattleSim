package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Combat {
    private final List<Pokemon> playerTeam;
    private final List<Pokemon> opponentTeam;
    private Pokemon activePlayerPokemon;
    private Pokemon activeOpponentPokemon;
    private Random random;

    public Combat(List<Pokemon> playerTeam, List<Pokemon> opponentTeam) {
        if (playerTeam.size() != 3 || opponentTeam.size() != 3) {
            throw new IllegalArgumentException("Each team must have exactly 3 Pokemon");
        }
        this.playerTeam = new ArrayList<>(playerTeam);
        this.opponentTeam = new ArrayList<>(opponentTeam);
        this.activePlayerPokemon = playerTeam.get(0);
        this.activeOpponentPokemon = opponentTeam.get(0);
        this.random = new Random();
    }
    public Pokemon getActivePlayerPokemon() {
        return activePlayerPokemon;
    }

    public Pokemon getActiveOpponentPokemon() {
        return activeOpponentPokemon;
    }

    public void startBattle() {
        System.out.println("3v3 Battle starts!");
        printTeamStatus();

        while (!isTeamDefeated(playerTeam) && !isTeamDefeated(opponentTeam)) {
            takeTurn();
        }

        if (isTeamDefeated(playerTeam)) {
            System.out.println("Player's team has been defeated! You lost!");
        } else {
            System.out.println("Opponent's team has been defeated! You win!");
        }
    }

    public void switchPlayerPokemon(int index) {
        if (index < 0 || index >= playerTeam.size()) {
            throw new IllegalArgumentException("Invalid Pokemon index");
        }
        Pokemon newPokemon = playerTeam.get(index);
        if (newPokemon.isFainted()) {
            System.out.println("Cannot switch to fainted Pokemon!");
            return;
        }
        if (newPokemon == activePlayerPokemon) {
            System.out.println("This Pokemon is already active!");
            return;
        }

        Pokemon previousPokemon = activePlayerPokemon;
        activePlayerPokemon = newPokemon;
        System.out.println(previousPokemon.getName() + " switches out with " + newPokemon.getName() + "!");

        // If opponent is faster, previous Pokemon takes the hit
        if (activeOpponentPokemon.getSpeed() > newPokemon.getSpeed()) {
            if (activeOpponentPokemon.getStatusEffect() == null || !activeOpponentPokemon.getStatusEffect().preventsAttack()) {
                activeOpponentPokemon.attack(previousPokemon, activeOpponentPokemon.getMoves().get(0));
            }
        } else {
            // If new Pokemon is faster, it takes the hit
            if (activeOpponentPokemon.getStatusEffect() == null || !activeOpponentPokemon.getStatusEffect().preventsAttack()) {
                activeOpponentPokemon.attack(newPokemon, activeOpponentPokemon.getMoves().get(0));
            }
        }
    }

    private void takeTurn() {
        // Apply status effects at the start of the turn
        activePlayerPokemon.applyStatusEffect();
        activeOpponentPokemon.applyStatusEffect();

        if (activePlayerPokemon.isFainted()) {
            handleFaintedPokemon(playerTeam, true);
            return;
        }
        if (activeOpponentPokemon.isFainted()) {
            handleFaintedPokemon(opponentTeam, false);
            return;
        }

        // Determine attack order
        Pokemon first, second;
        if (activePlayerPokemon.getSpeed() > activeOpponentPokemon.getSpeed() ||
                (activePlayerPokemon.getSpeed() == activeOpponentPokemon.getSpeed() && random.nextBoolean())) {
            first = activePlayerPokemon;
            second = activeOpponentPokemon;
        } else {
            first = activeOpponentPokemon;
            second = activePlayerPokemon;
        }

        executeAttacks(first, second);
    }

    private void executeAttacks(Pokemon first, Pokemon second) {
        if (first.getStatusEffect() == null || !first.getStatusEffect().preventsAttack()) {
            first.attack(second, first.getMoves().get(0));
        } else {
            System.out.println(first.getName() + " is unable to attack due to status effect!");
        }

        if (!second.isFainted() && (second.getStatusEffect() == null || !second.getStatusEffect().preventsAttack())) {
            second.attack(first, second.getMoves().get(0));
        }
    }

    private void handleFaintedPokemon(List<Pokemon> team, boolean isPlayer) {
        Pokemon newPokemon = findNextAlivePokemon(team);
        if (newPokemon != null) {
            if (isPlayer) {
                activePlayerPokemon = newPokemon;
            } else {
                activeOpponentPokemon = newPokemon;
            }
            System.out.println((isPlayer ? "Player" : "Opponent") + " sends out " + newPokemon.getName() + "!");
        }
    }

    private Pokemon findNextAlivePokemon(List<Pokemon> team) {
        return team.stream()
                .filter(p -> !p.isFainted())
                .findFirst()
                .orElse(null);
    }

    private boolean isTeamDefeated(List<Pokemon> team) {
        return team.stream().allMatch(Pokemon::isFainted);
    }

    private void printTeamStatus() {
        System.out.println("Player's team: " + formatTeamStatus(playerTeam));
        System.out.println("Opponent's team: " + formatTeamStatus(opponentTeam));
    }

    private String formatTeamStatus(List<Pokemon> team) {
        return String.join(" | ", team.stream()
                .map(p -> p.getName() + " (" + p.getHp() + "/" + p.getMaxHp() + ")")
                .toArray(String[]::new));
    }
}
