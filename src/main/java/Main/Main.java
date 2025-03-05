package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    public static void setRoot(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/views/homeview.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Pokémon Battle Simulator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}