package Main;

import controllers.BattleController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage primaryStage;

    public static void setRoot(String fxml) {
        setRoot(fxml, null);
    }

    public static void setRoot(String fxml, String teamName) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);

            if (teamName != null && loader.getController() instanceof BattleController) {
                BattleController controller = loader.getController();
                controller.setTeamName(teamName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        Main.primaryStage = primaryStage;
        primaryStage.setTitle("Pokemon Battle Simulator");
        setRoot("/views/homeview");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}