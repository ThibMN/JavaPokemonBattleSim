package controllers;

import Main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import models.*;
import java.util.Arrays;
import java.util.List;

public class BattleController {
    @FXML
    private Text playerPokemonName, playerPokemonHp, opponentPokemonName, opponentPokemonHp;
    @FXML
    private TextArea logArea;
    @FXML
    private Button move1, move2, move3, move4, runButton;
    @FXML
    private Button switch1, switch2, switch3, switch4, switch5;
    @FXML
    private void onSwitchPokemon1() { switchPokemon(0); }
    @FXML
    private void onSwitchPokemon2() { switchPokemon(1); }
    @FXML
    private void onSwitchPokemon3() { switchPokemon(2); }
    @FXML
    private void onSwitchPokemon4() { switchPokemon(3); }
    @FXML
    private void onSwitchPokemon5() { switchPokemon(4); }
    @FXML
    private Text playerTeamStatus, opponentTeamStatus;

    private Combat combat;
    private Pokemon playerPokemon;
    private Pokemon opponentPokemon;
    private boolean wasForcedSwitch = false;

    @FXML
    public void initialize() {
        List<Pokemon> playerTeam = createPlayerTeam();
        List<Pokemon> opponentTeam = createOpponentTeam();

        combat = new Combat(playerTeam, opponentTeam);
        playerPokemon = combat.getActivePlayerPokemon();
        opponentPokemon = combat.getActiveOpponentPokemon();

        updateInterface();
        initializeMoves();
        updateSwitchButtons();
        updateTeamStatus();
        log("Battle starts!");
    }

    private List<Pokemon> createPlayerTeam() {
        Pokemon pikachu = PokemonSetup.createPokemon(
                "Pikachu", 100, 55, 50, 40, 50, 90,
                new Type[]{Type.ELECTRIC},
                new Move("Thunder Shock", 40, Type.ELECTRIC, true, 1.0, null),
                new Move("Quick Attack", 30, Type.NORMAL, true, 1.0, null),
                new Move("Thunder Wave", 0, Type.ELECTRIC, false, 0.9, StatusEffect.PARALYSIS),
                new Move("Thunder", 90, Type.ELECTRIC, true, 0.8, null)
        );

        Pokemon bulbasaur = PokemonSetup.createPokemon(
                "Bulbasaur", 105, 49, 65, 49, 65, 45,
                new Type[]{Type.GRASS, Type.POISON},
                new Move("Vine Whip", 45, Type.GRASS, true, 1.0, null),
                new Move("Tackle", 40, Type.NORMAL, true, 1.0, null),
                new Move("Leech Seed", 0, Type.GRASS, false, 0.9, null),
                new Move("Growth", 0, Type.NORMAL, false, 1.0, null)
        );

        Pokemon charizard = PokemonSetup.createPokemon(
                "Charizard", 120, 84, 109, 78, 85, 100,
                new Type[]{Type.FIRE, Type.FLYING},
                new Move("Flamethrower", 90, Type.FIRE, true, 1.0, null),
                new Move("Air Slash", 75, Type.FLYING, true, 0.95, null),
                new Move("Dragon Claw", 80, Type.DRAGON, true, 1.0, null),
                new Move("Fire Blast", 110, Type.FIRE, true, 0.85, null)
        );

        Pokemon venusaur = PokemonSetup.createPokemon(
                "Venusaur", 120, 82, 100, 83, 100, 80,
                new Type[]{Type.GRASS, Type.POISON},
                new Move("Solar Beam", 120, Type.GRASS, true, 1.0, null),
                new Move("Sludge Bomb", 90, Type.POISON, true, 1.0, null),
                new Move("Earth Power", 90, Type.GROUND, true, 1.0, null),
                new Move("Giga Drain", 75, Type.GRASS, true, 1.0, null)
        );

        Pokemon blastoise = PokemonSetup.createPokemon(
                "Blastoise", 119, 83, 85, 100, 105, 78,
                new Type[]{Type.WATER},
                new Move("Hydro Pump", 110, Type.WATER, true, 0.8, null),
                new Move("Ice Beam", 90, Type.ICE, true, 1.0, null),
                new Move("Flash Cannon", 80, Type.STEEL, true, 1.0, null),
                new Move("Aqua Tail", 90, Type.WATER, true, 0.9, null)
        );
        return Arrays.asList(pikachu, bulbasaur, charizard, venusaur, blastoise);
    }

    private List<Pokemon> createOpponentTeam() {
        Pokemon gyarados = PokemonSetup.createPokemon(
                "Gyarados", 125, 125, 60, 79, 100, 81,
                new Type[]{Type.WATER, Type.FLYING},
                new Move("Waterfall", 80, Type.WATER, true, 1.0, null),
                new Move("Ice Fang", 65, Type.ICE, true, 0.95, null),
                new Move("Dragon Dance", 0, Type.DRAGON, false, 1.0, null),
                new Move("Crunch", 80, Type.DARK, true, 1.0, null)
        );

        Pokemon arcanine = PokemonSetup.createPokemon(
                "Arcanine", 115, 110, 100, 80, 80, 95,
                new Type[]{Type.FIRE},
                new Move("Flare Blitz", 120, Type.FIRE, true, 1.0, null),
                new Move("Extreme Speed", 80, Type.NORMAL, true, 1.0, null),
                new Move("Wild Charge", 90, Type.ELECTRIC, true, 1.0, null),
                new Move("Crunch", 80, Type.DARK, true, 1.0, null)
        );

        Pokemon alakazam = PokemonSetup.createPokemon(
                "Alakazam", 105, 50, 135, 45, 95, 120,
                new Type[]{Type.PSYCHIC},
                new Move("Psychic", 90, Type.PSYCHIC, true, 1.0, null),
                new Move("Shadow Ball", 80, Type.GHOST, true, 1.0, null),
                new Move("Energy Ball", 90, Type.GRASS, true, 1.0, null),
                new Move("Dazzling Gleam", 80, Type.FAIRY, true, 1.0, null)
        );

        Pokemon snorlax = PokemonSetup.createPokemon(
                "Snorlax", 160, 110, 65, 65, 110, 30,
                new Type[]{Type.NORMAL},
                new Move("Body Slam", 85, Type.NORMAL, true, 1.0, null),
                new Move("Earthquake", 100, Type.GROUND, true, 1.0, null),
                new Move("Rest", 0, Type.PSYCHIC, false, 1.0, null),
                new Move("Heavy Slam", 100, Type.STEEL, true, 1.0, null)
        );

        Pokemon dragonite = PokemonSetup.createPokemon(
                "Dragonite", 130, 134, 100, 95, 100, 80,
                new Type[]{Type.DRAGON, Type.FLYING},
                new Move("Dragon Claw", 80, Type.DRAGON, true, 1.0, null),
                new Move("Hurricane", 110, Type.FLYING, true, 0.7, null),
                new Move("Thunder Punch", 75, Type.ELECTRIC, true, 1.0, null),
                new Move("Ice Punch", 75, Type.ICE, true, 1.0, null)
        );

        return Arrays.asList(gyarados, arcanine, alakazam, snorlax, dragonite);
    }

    private void updateInterface() {
        playerPokemonName.setText(playerPokemon.getName());
        playerPokemonHp.setText(playerPokemon.getHp() + "/" + playerPokemon.getMaxHp());
        opponentPokemonName.setText(opponentPokemon.getName());
        opponentPokemonHp.setText(opponentPokemon.getHp() + "/" + opponentPokemon.getMaxHp());
    }

    private void initializeMoves() {
        List<Move> moves = playerPokemon.getMoves();
        move1.setText(moves.get(0).getName());
        move2.setText(moves.get(1).getName());
        move3.setText(moves.get(2).getName());
        move4.setText(moves.get(3).getName());
    }

    private void onMoveSelected(int moveIndex) {
        if (playerPokemon.isFainted()) {
            log(playerPokemon.getName() + " is fainted and cannot attack!");
            return;
        }

        Move selectedMove = playerPokemon.getMoves().get(moveIndex);
        log(playerPokemon.getName() + " uses " + selectedMove.getName() + "!");

        if (playerPokemon.getStatusEffect() == null || !playerPokemon.getStatusEffect().preventsAttack()) {
            playerPokemon.attack(opponentPokemon, selectedMove);

            if (selectedMove.getStatusEffect() != null) {
                opponentPokemon.setStatusEffect(selectedMove.getStatusEffect());
                log(opponentPokemon.getName() + " was inflicted with " + selectedMove.getStatusEffect() + "!");
            }
        } else {
            log(playerPokemon.getName() + " is unable to attack due to " + playerPokemon.getStatusEffect() + "!");
        }

        updateInterface();
        checkFainted();
        updateSwitchButtons();
        updateTeamStatus();

        if (!opponentPokemon.isFainted()) {
            opponentTurn();
        }
    }

    private void opponentTurn() {
        Move opponentMove = combat.selectOpponentMove();
        log(opponentPokemon.getName() + " uses " + opponentMove.getName() + "!");

        if (opponentPokemon.getStatusEffect() == null || !opponentPokemon.getStatusEffect().preventsAttack()) {
            opponentPokemon.attack(playerPokemon, opponentMove);

            if (opponentMove.getStatusEffect() != null) {
                playerPokemon.setStatusEffect(opponentMove.getStatusEffect());
                log(playerPokemon.getName() + " was inflicted with " + opponentMove.getStatusEffect() + "!");
            }
        } else {
            log(opponentPokemon.getName() + " is unable to attack due to " + opponentPokemon.getStatusEffect() + "!");
        }

        updateInterface();
        checkFainted();
        updateSwitchButtons();
        updateTeamStatus();
    }

    private void checkFainted() {
        if (opponentPokemon.isFainted() || playerPokemon.isFainted()) {
            if (combat.isTeamDefeated(combat.getOpponentTeam())) {
                log("Victory! All opponent's Pokemon are defeated!");
                disableMoves();
            } else if (combat.isTeamDefeated(combat.getPlayerTeam())) {
                log("Defeat! All your Pokemon have fainted!");
                disableMoves();
            } else {
                if (playerPokemon.isFainted()) {
                    wasForcedSwitch = true;
                    log(playerPokemon.getName() + " fainted! Choose your next Pokemon!");
                    disableMoves();
                    updateSwitchButtons();
                    updateTeamStatus();
                }
                if (opponentPokemon.isFainted()) {
                    switchOpponentPokemon();
                }
            }
        }
    }

    private void switchOpponentPokemon() {
        List<Pokemon> opponentTeam = combat.getOpponentTeam();
        Pokemon newOpponent = opponentTeam.stream()
                .filter(p -> !p.isFainted() && p != opponentPokemon)
                .findFirst()
                .orElse(null);

        if (newOpponent != null) {
            opponentPokemon = newOpponent;
            log("Opponent sends out " + opponentPokemon.getName() + "!");
            updateInterface();
            updateTeamStatus();
        }
    }

    private void disableMoves() {
        move1.setDisable(true);
        move2.setDisable(true);
        move3.setDisable(true);
        move4.setDisable(true);
        runButton.setDisable(true);
    }

    private void log(String message) {
        logArea.appendText(message + "\n");
    }

    @FXML
    private void onSelectMove1() { onMoveSelected(0); }

    @FXML
    private void onSelectMove2() { onMoveSelected(1); }

    @FXML
    private void onSelectMove3() { onMoveSelected(2); }

    @FXML
    private void onSelectMove4() { onMoveSelected(3); }

    @FXML
    private void onRun() {
        log("You fled from battle!");
        disableMoves();
        Main.setRoot("/views/homeview");
    }
    private void updateSwitchButtons() {
        List<Pokemon> team = combat.getPlayerTeam();
        switch1.setText(team.get(0).getName() + " (" + team.get(0).getHp() + "/" + team.get(0).getMaxHp() + ")");
        switch2.setText(team.get(1).getName() + " (" + team.get(1).getHp() + "/" + team.get(1).getMaxHp() + ")");
        switch3.setText(team.get(2).getName() + " (" + team.get(2).getHp() + "/" + team.get(2).getMaxHp() + ")");
        switch4.setText(team.get(3).getName() + " (" + team.get(3).getHp() + "/" + team.get(3).getMaxHp() + ")");
        switch5.setText(team.get(4).getName() + " (" + team.get(4).getHp() + "/" + team.get(4).getMaxHp() + ")");

    // Disable button for current Pokemon and fainted Pokemon
        for (int i = 0; i < team.size(); i++) {
            Button switchButton = getSwitchButton(i);
            Pokemon pokemon = team.get(i);
            switchButton.setDisable(pokemon == playerPokemon || pokemon.isFainted());
        }
    }

    private Button getSwitchButton(int index) {
        switch (index) {
            case 0: return switch1;
            case 1: return switch2;
            case 2: return switch3;
            case 3: return switch4;
            case 4: return switch5;
            default: throw new IllegalArgumentException("Invalid switch button index");
        }
    }

    private void updateTeamStatus() {
        int playerAlive = countAlivePokemon(combat.getPlayerTeam());
        int opponentAlive = countAlivePokemon(combat.getOpponentTeam());

        playerTeamStatus.setText(playerAlive + "/5 Pokemon");
        opponentTeamStatus.setText(opponentAlive + "/5 Pokemon");
    }

    private int countAlivePokemon(List<Pokemon> team) {
        return (int) team.stream().filter(p -> !p.isFainted()).count();
    }

    private void switchPokemon(int index) {
        Pokemon newPokemon = combat.getPlayerTeam().get(index);

        // If current Pokemon is fainted, allow switching regardless
        if (!playerPokemon.isFainted()) {
            if (newPokemon == playerPokemon) {
                log(newPokemon.getName() + " is already in battle!");
                return;
            }
        }

        if (newPokemon.isFainted()) {
            log(newPokemon.getName() + " is fainted and cannot battle!");
            return;
        }

        playerPokemon = newPokemon;
        log("Go, " + playerPokemon.getName() + "!");

        updateInterface();
        initializeMoves();
        updateSwitchButtons();
        updateTeamStatus();

        // Only do opponent turn if it wasn't a forced switch (fainted Pokemon)
        if (!combat.isTeamDefeated(combat.getOpponentTeam()) &&
                !playerPokemon.isFainted() &&
                !wasForcedSwitch) {
            opponentTurn();
        }
        wasForcedSwitch = false;
    }
}