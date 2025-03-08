package controllers;

import Main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HomeController {

    @FXML
    private ComboBox<String> teamSelector;

    @FXML
    public void initialize() {
        loadSavedTeams();
    }

    private void loadSavedTeams() {
        File teamDir = new File("teams");
        if (teamDir.exists() && teamDir.isDirectory()) {
            List<String> teamNames = Arrays.stream(teamDir.listFiles())
                    .filter(file -> file.getName().endsWith(".team"))
                    .map(file -> file.getName().replace(".team", ""))
                    .collect(Collectors.toList());
            teamSelector.getItems().setAll(teamNames);
        }
    }

    public void goToTeamCreation(ActionEvent event) {
        Main.setRoot("/views/TeamCreation");
    }

    public void goToBattle(ActionEvent event) {
        String selectedTeam = teamSelector.getValue();
        if (selectedTeam != null) {
            Main.setRoot("/views/BattleView", selectedTeam);
        } else {
            showAlert("No Team Selected", "Please select a team to start the battle.");
        }
    }

    public void exitApp(ActionEvent event) {
        System.exit(0);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}