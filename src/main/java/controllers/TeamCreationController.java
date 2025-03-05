package controllers;

import Main.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Node;

public class TeamCreationController {

    @FXML
    private TextField teamNameField;

    @FXML
    private ComboBox<String> pokemonSelector;

    @FXML
    private ListView<String> teamList;

    @FXML
    private Button addPokemonButton;

    @FXML
    private Button submitTeamButton;

    @FXML
    private Button backToMenuButton;

    private ObservableList<String> teamMembers = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Remplir la liste des Pokémon disponibles
        pokemonSelector.setItems(FXCollections.observableArrayList(
                "Pikachu", "Bulbizarre", "Salamèche", "Carapuce",
                "Evoli", "Dracolosse", "Mewtwo", "Ronflex", "Florizarre",
                "Tortank", "Flambusard"
        ));

        // Attacher la liste des membres de l'équipe à la ListView
        teamList.setItems(teamMembers);
    }

    @FXML
    private void onAddPokemon() {
        String selectedPokemon = pokemonSelector.getValue();

        if (selectedPokemon != null && !teamMembers.contains(selectedPokemon)) {
            if (teamMembers.size() < 5) {
                // Ajoute le Pokémon à l'équipe
                teamMembers.add(selectedPokemon);
                pokemonSelector.setValue(null); // Réinitialise la sélection
            } else {
                // Alerte si l'équipe a déjà 5 Pokémon
                showAlert(Alert.AlertType.WARNING, "Équipe complète",
                        "L'équipe ne peut pas avoir plus de 5 Pokémon !");
            }
        } else if (selectedPokemon == null) {
            // Alerte si aucun Pokémon n'est sélectionné
            showAlert(Alert.AlertType.WARNING, "Sélection invalide",
                    "Veuillez sélectionner un Pokémon à ajouter.");
        } else {
            // Alerte si le Pokémon est déjà ajouté
            showAlert(Alert.AlertType.WARNING, "Duplication",
                    "Ce Pokémon est déjà dans l'équipe.");
        }
    }

    @FXML
    private void onSubmitTeam() {
        String teamName = teamNameField.getText();

        if (teamName.isEmpty()) {
            // Alerte si le nom de l'équipe est vide
            showAlert(Alert.AlertType.ERROR, "Erreur", "Le nom de l'équipe est requis !");
            return;
        }

        if (teamMembers.size() < 5) {
            // Alerte si l'équipe n'a pas 5 Pokémon
            showAlert(Alert.AlertType.ERROR, "Erreur",
                    "Vous devez avoir une équipe complète de 5 Pokémon pour valider.");
            return;
        }

        // Affiche un message de succès
        showAlert(Alert.AlertType.INFORMATION, "Succès",
                "Votre équipe \"" + teamName + "\" a été créée avec succès !");

        // Réinitialise les champs pour une nouvelle équipe
        teamNameField.clear();
        pokemonSelector.setValue(null);
        teamMembers.clear();
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
        // Logique pour retourner au menu principal
        System.out.println("Retour au menu principal...");

        // Si vous changez de stage ou de vue, vous pouvez fermer la fenêtre actuelle :
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