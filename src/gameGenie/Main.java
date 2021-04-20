package gameGenie;

/**
 * last updated 04/20/2021
 * Author(s) Ian Holder,
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameGenieController.getInstance().setPrimaryStage(primaryStage);
    }

    @Override
    public void stop(){
        UserController.programClose();
        System.out.println("File Saved");
    }

}