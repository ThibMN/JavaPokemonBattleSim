package controllers;

import javafx.event.ActionEvent;
import Main.Main;

public class HomeController {

    public void goToTeamCreation(ActionEvent event) {
        Main.setRoot("/views/TeamCreation");
    }

    public void goToBattle(ActionEvent event) {
        Main.setRoot("/views/BattleView");
    }

    public void exitApp(ActionEvent event) {
        System.exit(0);
    }
}