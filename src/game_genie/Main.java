package game_genie;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * last updated 03/25/2021
 * Author(s) Ian Holder,
 */

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameGenieController.getInstance().setPrimaryStage(primaryStage);
    }

}