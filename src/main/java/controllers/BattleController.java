package controllers;

import Main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import models.Pokemon;
import models.Type;
import models.Move;
import models.StatusEffect;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BattleController {

    @FXML
    private Text playerPokemonName, playerPokemonHp, opponentPokemonName, opponentPokemonHp;

    @FXML
    private TextArea logArea;

    @FXML
    private Button move1, move2, move3, move4, runButton;

    private Pokemon playerPokemon;
    private Pokemon opponentPokemon;

    private final Random random = new Random();

    @FXML
    public void initialize() {
        // Initialisation des Pokémon depuis `models.Pokemon`
        playerPokemon = createPlayerPokemon();
        opponentPokemon = createOpponentPokemon();

        // Initialisation du jeu (interface utilisateur et moves)
        updateInterface();
        initializeMoves();
    }

    private Pokemon createPlayerPokemon() {
        // Définir les moves de Pikachu
        Move move1 = new Move("Éclair", 40, Type.ELECTRIC, true, 1.0, null);
        Move move2 = new Move("Vive-Attaque", 30, Type.NORMAL, true, 1.0, null);
        Move move3 = new Move("Cage-Éclair", 0, Type.ELECTRIC, false, 0.9, null);
        Move move4 = new Move("Tonnerre", 90, Type.ELECTRIC, true, 0.8, null);

        List<Move> pikachuMoves = Arrays.asList(move1, move2, move3, move4);
        Type[] pikachuTypes = {Type.ELECTRIC};

        // Retourne un Pokémon Pikachu respectant le constructeur donné
        return new Pokemon("Pikachu", 100, 55, 50, 40, 50, 90, pikachuTypes, pikachuMoves);
    }

    private Pokemon createOpponentPokemon() {
        // Définir les moves de Salamèche
        Move move1 = new Move("Flammèche", 40, Type.FIRE, true, 1.0, null);
        Move move2 = new Move("Griffe", 30, Type.NORMAL, true, 1.0, null);
        Move move3 = new Move("Lance-Flammes", 90, Type.FIRE, true, 0.8, null);
        Move move4 = new Move("Grondement", 0, Type.NORMAL, false, 1.0, null);

        List<Move> charmeleonMoves = Arrays.asList(move1, move2, move3, move4);
        Type[] charmeleonTypes = {Type.FIRE};

        // Retourne un Pokémon Salamèche respectant le constructeur donné
        return new Pokemon("Salamèche", 100, 52, 60, 43, 50, 65, charmeleonTypes, charmeleonMoves);
    }

    private void updateInterface() {
        // Met à jour les noms et PV des Pokémon
        playerPokemonName.setText("Votre Pokémon : " + playerPokemon.getName());
        playerPokemonHp.setText("PV : " + playerPokemon.getHp() + "/" + playerPokemon.getMaxHp());

        opponentPokemonName.setText("Adversaire : " + opponentPokemon.getName());
        opponentPokemonHp.setText("PV : " + opponentPokemon.getHp() + "/" + opponentPokemon.getMaxHp());
    }

    private void initializeMoves() {
        // Configure les boutons d'attaques avec les moves du joueur
        Move[] moves = playerPokemon.getMoves().toArray(new Move[0]);
        move1.setText(moves[0].getName());
        move2.setText(moves[1].getName());
        move3.setText(moves[2].getName());
        move4.setText(moves[3].getName());
    }

    private void onMoveSelected(int moveIndex) {
        // Appliquer l'attaque sélectionnée par le joueur
        if (playerPokemon.isFainted()) {
            log("Votre Pokémon est KO et ne peut plus attaquer !");
            return;
        }

        Move selectedMove = playerPokemon.getMoves().get(moveIndex);
        log(playerPokemon.getName() + " utilise " + selectedMove.getName() + " !");

        // Applique les dégâts au Pokémon adverse
        opponentPokemon.takeDamage(selectedMove.getPower());
        log(opponentPokemon.getName() + " subit " + selectedMove.getPower() + " dégâts !");
        updateInterface();

        if (opponentPokemon.isFainted()) {
            log("L'adversaire " + opponentPokemon.getName() + " est KO ! Vous avez gagné !");
            disableMoves();
            return;
        }

        // Tour de l'adversaire
        opponentTurn();
    }

    private void opponentTurn() {
        // L'adversaire attaque avec un move aléatoire
        if (opponentPokemon.isFainted()) return;

        Move opponentMove = opponentPokemon.getMoves().get(random.nextInt(opponentPokemon.getMoves().size()));
        log(opponentPokemon.getName() + " utilise " + opponentMove.getName() + " !");

        playerPokemon.takeDamage(opponentMove.getPower());
        log(playerPokemon.getName() + " subit " + opponentMove.getPower() + " dégâts !");
        updateInterface();

        if (playerPokemon.isFainted()) {
            log(playerPokemon.getName() + " est KO ! Vous avez perdu !");
            disableMoves();
        }
    }

    private void disableMoves() {
        // Désactive les boutons de moves
        move1.setDisable(true);
        move2.setDisable(true);
        move3.setDisable(true);
        move4.setDisable(true);
    }

    private void log(String message) {
        // Ajoute un message dans la zone de log
        logArea.appendText(message + "\n");
    }

    @FXML
    private void onSelectMove1() {
        onMoveSelected(0);
    }

    @FXML
    private void onSelectMove2() {
        onMoveSelected(1);
    }

    @FXML
    private void onSelectMove3() {
        onMoveSelected(2);
    }

    @FXML
    private void onSelectMove4() {
        onMoveSelected(3);
    }

    @FXML
    private void onRun() {
        log("Vous avez fui le combat !");
        disableMoves();
        Main.setRoot("/views/homeview");
    }
}