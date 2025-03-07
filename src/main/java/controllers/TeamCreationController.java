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
import java.util.List;

public class TeamCreationController {

    @FXML
    private TextField teamNameField;

    @FXML
    private ComboBox<String> pokemonSelector;

    @FXML
    private ListView<Pokemon> teamList; // Modifié pour utiliser directement une liste de Pokémon

    @FXML
    private Button addPokemonButton;

    @FXML
    private Button submitTeamButton;

    @FXML
    private Button modifyMovesetButton; // Bouton pour modifier moveset

    private ObservableList<Pokemon> teamMembers = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Exemple de Pokémon disponibles
        pokemonSelector.setItems(FXCollections.observableArrayList(
                "Pikachu", "Salamèche", "Carapuce", "Florizarre"
        ));

        teamList.setItems(teamMembers); // Lie membres de l'équipe
    }

    @FXML
    private void onAddPokemon() {
        String selectedPokemon = pokemonSelector.getValue();

        if (selectedPokemon != null && teamMembers.size() < 5) {
            Pokemon newPokemon = new Pokemon(selectedPokemon, 100, 50, 60, 40, 50, 50, null, new ArrayList<>());
            teamMembers.add(newPokemon);
            pokemonSelector.setValue(null);
        }
    }

    @FXML
    private void onModifyMoveset() {
        // Récupère le Pokémon sélectionné dans la liste
        Pokemon selectedPokemon = teamList.getSelectionModel().getSelectedItem();
        if (selectedPokemon != null) {
            try {
                // Charge la fenêtre d'édition du moveset
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/editMoveset.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));
                stage.setTitle("Modifier Moveset");

                // Passe le Pokémon à la fenêtre
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

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}