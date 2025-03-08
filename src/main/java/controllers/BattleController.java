package controllers;

import Main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import models.*;
import pokemons.*;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class BattleController {
    @FXML
    private Text playerPokemonName, playerPokemonHp, opponentPokemonName, opponentPokemonHp;
    @FXML
    private TextArea logArea;
    @FXML
    private Button moveButton1, moveButton2, moveButton3, moveButton4, runButton;
    @FXML
    private Button switchButton1, switchButton2, switchButton3, switchButton4, switchButton5;
    @FXML
    private Text playerTeamStatus, opponentTeamStatus;

    private Combat combat;
    private Pokemon playerPokemon;
    private Pokemon opponentPokemon;
    private boolean wasForcedSwitch = false;

    private String teamName;

    public void setTeamName(String teamName) {
        this.teamName = teamName;
        initializeBattle();
    }

    private void initializeBattle() {
        try {
            List<Pokemon> playerTeam = loadTeam(teamName);
            if (playerTeam == null || playerTeam.isEmpty()) {
                throw new IllegalStateException("Failed to load player team");
            }

            List<Pokemon> opponentTeam = createRandomTeam();

            combat = new Combat(playerTeam, opponentTeam);
            playerPokemon = combat.getActivePlayerPokemon();
            opponentPokemon = combat.getActiveOpponentPokemon();

            updateInterface();
            initializeMoves();
            updateSwitchButtons();
            updateTeamStatus();
            log("Battle starts with " + teamName + "'s team!");
        } catch (Exception e) {
            System.err.println("Error initializing battle: " + e.getMessage());
            e.printStackTrace();
            // Gérer l'erreur de manière appropriée, par exemple retourner au menu principal
            Main.setRoot("/views/homeview");
        }
    }

    private List<Pokemon> loadTeam(String teamName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("teams/" + teamName + ".team"))) {
            List<Pokemon> team = (List<Pokemon>) ois.readObject();
            if (team == null || team.isEmpty()) {
                throw new Exception("Invalid team data");
            }
            // Initialize moves for each Pokemon if needed
            for (Pokemon pokemon : team) {
                if (pokemon.getMoves() == null) {
                    pokemon.initializeDefaultMoves();
                }
            }
            return team;
        } catch (Exception e) {
            System.err.println("Error loading team: " + e.getMessage());
            e.printStackTrace();
            return createRandomTeam();
        }
    }

    private List<Pokemon> createRandomTeam() {
        List<Class<?>> allPokemonClasses = Arrays.asList(
                Blastoise.class, Bulbasaur.class, Charmander.class, Dragonite.class, Eevee.class,
                Mewtwo.class, Pikachu.class, Snorlax.class, Squirtle.class, Venusaur.class
        );

        Random random = new Random();
        List<Pokemon> collect = random.ints(0, allPokemonClasses.size())
                .distinct()
                .limit(5)
                .mapToObj(allPokemonClasses::get)
                .map(clazz -> createPokemonInstance((Class<? extends Pokemon>) clazz))
                .collect(Collectors.toList());
        return collect;
    }

    private Pokemon createPokemonInstance(Class<? extends Pokemon> pokemonClass) {
        try {
            return pokemonClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create Pokemon instance", e);
        }
    }

    private void updateInterface() {
        playerPokemonName.setText(playerPokemon.getName());
        playerPokemonHp.setText(playerPokemon.getHp() + "/" + playerPokemon.getMaxHp());
        opponentPokemonName.setText(opponentPokemon.getName());
        opponentPokemonHp.setText(opponentPokemon.getHp() + "/" + opponentPokemon.getMaxHp());
    }

    private void initializeMoves() {
        List<Move> pokemonMoves = playerPokemon.getMoves();
        if (pokemonMoves == null || pokemonMoves.isEmpty()) {
            playerPokemon.initializeDefaultMoves();
            pokemonMoves = playerPokemon.getMoves();
        }

        moveButton1.setText(pokemonMoves.get(0).getName());
        moveButton2.setText(pokemonMoves.get(1).getName());
        moveButton3.setText(pokemonMoves.get(2).getName());
        moveButton4.setText(pokemonMoves.get(3).getName());
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
        moveButton1.setDisable(true);
        moveButton2.setDisable(true);
        moveButton3.setDisable(true);
        moveButton4.setDisable(true);
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

    private void updateSwitchButtons() {
        List<Pokemon> team = combat.getPlayerTeam();
        switchButton1.setText(team.get(0).getName() + " (" + team.get(0).getHp() + "/" + team.get(0).getMaxHp() + ")");
        switchButton2.setText(team.get(1).getName() + " (" + team.get(1).getHp() + "/" + team.get(1).getMaxHp() + ")");
        switchButton3.setText(team.get(2).getName() + " (" + team.get(2).getHp() + "/" + team.get(2).getMaxHp() + ")");
        switchButton4.setText(team.get(3).getName() + " (" + team.get(3).getHp() + "/" + team.get(3).getMaxHp() + ")");
        switchButton5.setText(team.get(4).getName() + " (" + team.get(4).getHp() + "/" + team.get(4).getMaxHp() + ")");

        // Disable button for current Pokemon and fainted Pokemon
        for (int i = 0; i < team.size(); i++) {
            Button switchButton = getSwitchButton(i);
            Pokemon pokemon = team.get(i);
            switchButton.setDisable(pokemon == playerPokemon || pokemon.isFainted());
        }
    }

    private Button getSwitchButton(int index) {
        switch (index) {
            case 0: return switchButton1;
            case 1: return switchButton2;
            case 2: return switchButton3;
            case 3: return switchButton4;
            case 4: return switchButton5;
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
        enableMoveButtons();

        // Only do opponent turn if it wasn't a forced switch (fainted Pokemon)
        if (!combat.isTeamDefeated(combat.getOpponentTeam()) &&
                !playerPokemon.isFainted() &&
                !wasForcedSwitch) {
            opponentTurn();
        }
        wasForcedSwitch = false;
    }

    private void enableMoveButtons() {
        moveButton1.setDisable(false);
        moveButton2.setDisable(false);
        moveButton3.setDisable(false);
        moveButton4.setDisable(false);
    }
}