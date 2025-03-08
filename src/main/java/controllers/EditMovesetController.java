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
        List<Move> updatedMoves = new ArrayList<>();
        for (String moveName : movesetList.getItems()) {
            // Créer le bon mouvement en fonction du nom
            updatedMoves.add(createMoveByName(moveName));
        }
        currentPokemon.setMoves(updatedMoves);
        Stage stage = (Stage) movesetList.getScene().getWindow();
        stage.close();
    }

    private Move createMoveByName(String name) {
        switch (name) {
            case "Thunderbolt":
                return new Thunderbolt();
            case "Flamethrower":
                return new Flamethrower();
            case "Surf":
                return new Surf();
            case "Ice Beam":
                return new IceBeam();
            case "Solar Beam":
                return new SolarBeam();
            case "Earthquake":
                return new Earthquake();
            case "Psychic":
                return new Psychic();
            case "Fly":
                return new Fly();
            case "Poison Jab":
                return new PoisonJab();
            case "Close Combat":
                return new CloseCombat();
            default:
                throw new IllegalArgumentException("Unknown move: " + name);
        }
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}