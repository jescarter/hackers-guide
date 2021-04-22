package gameGenie;

/*
 * last updated 04/21/2021
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

    //on close route through the game genie controller that the application is closing
    @Override
    public void stop(){
        GameGenieController.applicationClosing();
        System.out.println("File Saved");
    }

}