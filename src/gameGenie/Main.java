package gameGenie;

/*
 * last updated 04/21/2021
 * Author(s) Ian Holder,
 */

import Testing.Test;
import javafx.application.Application;
import javafx.stage.Stage;
import user.SaveDataTranslator;

public class Main extends Application {

    public static void main(String[] args) {
        Test.test();
        //launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GameGenieController.getInstance().setPrimaryStage(primaryStage);
    }

    //on close route through the game genie controller that the application is closing
    @Override
    public void stop(){
        //routing the closure call to inform the user package to save it's data
        GameGenieController.applicationClosing();
        System.out.println("File Saved");
    }

}