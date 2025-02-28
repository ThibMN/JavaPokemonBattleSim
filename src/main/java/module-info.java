module com.example.javapokemonbattlesim {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.javapokemonbattlesim to javafx.fxml;
    exports com.example.javapokemonbattlesim;
}