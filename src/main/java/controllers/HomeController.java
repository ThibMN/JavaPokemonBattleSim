package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HomeController {

    public void goToTeamCreation(ActionEvent event) {
        Main.setRoot("/views/TeamCreation");
    }

    public void goToStatistics(ActionEvent event) {
        Main.setRoot("/views/Statistics");
    }

    public void exitApp(ActionEvent event) {
        System.exit(0);
    }
}