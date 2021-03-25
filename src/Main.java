package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * last updated 03/25/2021
 * Author(s) Ian Holder,
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
        //GameInfoTranslator.getVideoGameInfo();
        //GenreTranslator.getGenres();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("startScreen.fxml"));

    }
}