package controllers;

import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.Pokemon;

import java.util.ArrayList;

public class TeamCreationController {

    @FXML
    private TextField teamNameField;

    @FXML
    private ComboBox<String> pokemonSelector;

    @FXML
    private ListView<Pokemon> teamList;

    @FXML
    private Button addPokemonButton;

    @FXML
    private Button submitTeamButton;

    @FXML
    private Button backToMenuButton;

    @FXML
    private Button modifyMovesetButton;

    private ObservableList<Pokemon> teamMembers = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Exemple de Pokémon disponibles
        pokemonSelector.setItems(FXCollections.observableArrayList(
                "Pikachu", "Salamèche", "Carapuce", "Florizarre"
        ));

        teamList.setItems(teamMembers); // Lie membres de l'équipe

        // Définir une cellFactory pour afficher le nom du Pokémon
        teamList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Pokemon pokemon, boolean empty) {
                super.updateItem(pokemon, empty);
                if (empty || pokemon == null) {
                    setText(null);
                } else {
                    setText(pokemon.getName());
                }
            }
        });
    }

    @FXML
    private void onAddPokemon() {
        String selectedPokemon = pokemonSelector.getValue();

        if (selectedPokemon != null && teamMembers.size() < 5) {
            Pokemon newPokemon = new Pokemon(selectedPokemon, 100, 50, 60, 40, 50, 50, null, new ArrayList<>());
            teamMembers.add(newPokemon);
            pokemonSelector.setValue(null);
        } else if (selectedPokemon == null) {
            showAlert(Alert.AlertType.WARNING, "Sélection invalide", "Veuillez sélectionner un Pokémon à ajouter.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Équipe complète", "L'équipe ne peut pas avoir plus de 5 Pokémon !");
        }
    }

    @FXML
    private void onSubmitTeam() {
        String teamName = teamNameField.getText();

        if (teamName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom de l'équipe est requis !");
            return;
        }

        if (teamMembers.size() < 5) {
            showAlert(Alert.AlertType.ERROR, "Erreur", "Vous devez avoir une équipe complète de 5 Pokémon pour valider.");
            return;
        }

        showAlert(Alert.AlertType.INFORMATION, "Succès", "Votre équipe \"" + teamName + "\" a été créée avec succès !");

        teamNameField.clear();
        pokemonSelector.setValue(null);
        teamMembers.clear();
    }

    @FXML
    private void onModifyMoveset() {
        Pokemon selectedPokemon = teamList.getSelectionModel().getSelectedItem();
        if (selectedPokemon != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/editMoveset.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Modifier Moveset");

                EditMovesetController controller = loader.getController();
                controller.setPokemon(selectedPokemon);

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Aucun Pokémon Sélectionné", "Veuillez sélectionner un Pokémon à modifier.");
        }
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
        System.out.println("Retour au menu principal...");
        Main.setRoot("/views/homeview");
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}