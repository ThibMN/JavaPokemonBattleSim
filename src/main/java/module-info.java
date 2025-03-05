module com.example.javapokemonbattlesim {
    requires javafx.controls;
    requires javafx.fxml;

    exports controllers; // Permet d'exposer le package controllers pour d'autres modules
    exports Main;        // Si ce n'était pas déjà fait pour votre classe Main

    opens controllers to javafx.fxml; // Spécifiquement nécessaire pour le FXML loader
}
