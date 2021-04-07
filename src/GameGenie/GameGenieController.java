package src.gameGenie;
/**
 * A controller to set the state of the application and change between views.
 * last updated 04/02/2021
 * Author(s) Ian Holder,
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameGenieController {
    private Stage primaryStage;
    private static GameGenieController controller = null;

    GameGenieController() {
        this.primaryStage = new Stage();
    }

    //will creat a new scene from an inputted file name and update the stage
    private void updateStage(String _fxmlFile) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(_fxmlFile));
            Pane pane = myLoader.load();
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    //the loading protocol from start to game picker
    public void changeSceneIntoGamePicker(){
        updateStage("/src/gamePickerScreen.fxml");
    }

    public void changeSceneIntoGameRecommendation(){
        updateStage("/src/gameRecommendationScreen.fxml");
    }

    //make the GameGenieController a singleton
    public static GameGenieController getInstance(){
        if(controller == null){
            controller = new GameGenieController();
        }
        return controller;
    }

    public void setPrimaryStage(Stage _stage){
        this.primaryStage = _stage;
        primaryStage.setTitle("Game Genie");
        updateStage("/src/startScreen.fxml");
        primaryStage.show();
    }
}
