package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Move;
import models.Pokemon;

public class EditMovesetController {

    @FXML
    private Label pokemonNameLabel;

    @FXML
    private ListView<String> movesetList;

    @FXML
    private TextField newMoveField;

    private Pokemon currentPokemon; // Référence au Pokémon modifiable

    public void setPokemon(Pokemon pokemon) {
        this.currentPokemon = pokemon;
        pokemonNameLabel.setText("Modifier le Moveset de " + pokemon.getName());

        // Remplit la liste des moves actuels
        ObservableList<String> moves = FXCollections.observableArrayList();
        for (Move move : pokemon.getMoves()) {
            moves.add(move.getName());
        }
        movesetList.setItems(moves);
    }

    @FXML
    private void onAddMove() {
        String newMove = newMoveField.getText();
        if (!newMove.isEmpty()) {
            movesetList.getItems().add(newMove);
            newMoveField.clear();
        }
    }

    @FXML
    private void onSaveAndClose() {
        // Met à jour les movesets du Pokémon
        currentPokemon.getMoves().clear(); // Efface les anciens moves
        for (String moveName : movesetList.getItems()) {
            // Ajoute de nouveaux moves (simplement par nom ici, pourrait être enrichi)
            currentPokemon.getMoves().add(new Move(moveName, 50, null, true, 0, null));
        }

        // Ferme la fenêtre
        Stage stage = (Stage) movesetList.getScene().getWindow();
        stage.close();
    }
}