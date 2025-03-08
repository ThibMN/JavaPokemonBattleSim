package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Move;
import models.Pokemon;
import moves.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditMovesetController {

    @FXML
    private Label pokemonNameLabel;

    @FXML
    private ListView<String> movesetList;

    @FXML
    private ComboBox<String> availableMovesComboBox;

    private Pokemon currentPokemon; // Reference to the modifiable Pokémon

    public void setPokemon(Pokemon pokemon) {
        this.currentPokemon = pokemon;
        pokemonNameLabel.setText("Modifier le Moveset de " + pokemon.getName());

        // Fill the list of current moves
        ObservableList<String> moves = FXCollections.observableArrayList();
        for (Move move : pokemon.getMoves()) {
            moves.add(move.getName());
        }
        movesetList.setItems(moves);

        // Load available moves
        loadAvailableMoves();
    }

    private void loadAvailableMoves() {
        List<Move> availableMoves = Arrays.asList(
                new Thunderbolt(),
                new Flamethrower(),
                new Surf(),
                new IceBeam(),
                new SolarBeam(),
                new Earthquake(),
                new Psychic(),
                new Fly(),
                new PoisonJab(),
                new CloseCombat()
        );

        ObservableList<String> moveNames = FXCollections.observableArrayList();
        for (Move move : availableMoves) {
            moveNames.add(move.getName());
        }

        availableMovesComboBox.setItems(moveNames);
    }

    @FXML
    private void onAddMove() {
        String newMove = availableMovesComboBox.getValue();
        if (newMove != null && movesetList.getItems().size() < 4) {
            movesetList.getItems().add(newMove);
            availableMovesComboBox.setValue(null);
        } else if (movesetList.getItems().size() >= 4) {
            showAlert(Alert.AlertType.WARNING, "Limite atteinte", "Vous ne pouvez ajouter que 4 moves.");
        }
    }

    @FXML
    private void onRemoveMove() {
        String selectedMove = movesetList.getSelectionModel().getSelectedItem();
        if (selectedMove != null) {
            movesetList.getItems().remove(selectedMove);
        } else {
            showAlert(Alert.AlertType.WARNING, "Aucun move sélectionné", "Veuillez sélectionner un move à supprimer.");
        }
    }

    @FXML
    private void onSaveAndClose() {
        // Create a new list for the updated moves
        List<Move> updatedMoves = new ArrayList<>();
        for (String moveName : movesetList.getItems()) {
            // Add new moves (simply by name here, could be enriched)
            updatedMoves.add(new Move(moveName, 50, null, true, 0, null));
        }

        // Set the new list of moves to the Pokémon
        currentPokemon.setMoves(updatedMoves);

        // Close the window
        Stage stage = (Stage) movesetList.getScene().getWindow();
        stage.close();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}