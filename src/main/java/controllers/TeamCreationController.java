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
import pokemons.*;

import java.util.List;

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
        // Load available Pokémon from the pokemons package
        loadAvailablePokemons();

        teamList.setItems(teamMembers); // Link team members

        // Define a cellFactory to display the Pokémon name
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

    private void loadAvailablePokemons() {
        List<Pokemon> availablePokemons = List.of(
                new Pikachu(),
                new Bulbasaur(),
                new Charmander(),
                new Squirtle(),
                new Eevee(),
                new Dragonite()
        );

        ObservableList<String> pokemonNames = FXCollections.observableArrayList();
        for (Pokemon pokemon : availablePokemons) {
            pokemonNames.add(pokemon.getName());
        }

        pokemonSelector.setItems(pokemonNames);
    }

    @FXML
    private void onAddPokemon() {
        String selectedPokemon = pokemonSelector.getValue();

        if (selectedPokemon != null && teamMembers.size() < 5) {
            Pokemon newPokemon = createPokemonByName(selectedPokemon);
            teamMembers.add(newPokemon);
            pokemonSelector.setValue(null);
        } else if (selectedPokemon == null) {
            showAlert(Alert.AlertType.WARNING, "Invalid Selection", "Please select a Pokémon to add.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Team Full", "The team cannot have more than 5 Pokémon!");
        }
    }

    private Pokemon createPokemonByName(String name) {
        switch (name) {
            case "Pikachu": return new Pikachu();
            case "Bulbasaur": return new Bulbasaur();
            case "Charmander": return new Charmander();
            case "Squirtle": return new Squirtle();
            case "Eevee": return new Eevee();
            case "Dragonite": return new Dragonite();
            default: throw new IllegalArgumentException("Unknown Pokémon: " + name);
        }
    }

    @FXML
    private void onSubmitTeam() {
        String teamName = teamNameField.getText();

        if (teamName.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Team name is required!");
            return;
        }

        if (teamMembers.size() < 5) {
            showAlert(Alert.AlertType.ERROR, "Error", "You must have a full team of 5 Pokémon to submit.");
            return;
        }

        showAlert(Alert.AlertType.INFORMATION, "Success", "Your team \"" + teamName + "\" has been successfully created!");

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
                stage.setTitle("Edit Moveset");

                EditMovesetController controller = loader.getController();
                controller.setPokemon(selectedPokemon);

                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Pokémon Selected", "Please select a Pokémon to modify.");
        }
    }

    @FXML
    private void onBackToMenu(ActionEvent event) {
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