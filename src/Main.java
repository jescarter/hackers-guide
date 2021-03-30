package src;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * last updated 03/25/2021
 * Author(s) Ian Holder,
 */

public class Main extends Application {
    private final double STAGEWIDTH = 600.0;
    private final double STAGEHEIGHT = 800.0;
    static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
        //GameInfoTranslator.getVideoGameInfo();
        //GenreTranslator.getGenres();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("startScreen.fxml"));
        primaryStage.setTitle("Game Genie");
        primaryStage.setScene(new Scene(root,STAGEWIDTH,STAGEHEIGHT));
        primaryStage.show();

    }

    public void changeScene(String _scenefxml) throws Exception{
        Parent pane = FXMLLoader.load(getClass().getResource(_scenefxml));
        primaryStage.getScene().setRoot(pane);
    }
}