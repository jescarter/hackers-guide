package GameGenie;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * an application controller
 * last updated 04/02/2021
 * Author(s) Ian Holder,
 */

public class GameGenieController {
    private Stage primaryStage;
    private static GameGenieController controller = null;

    GameGenieController() {
        this.primaryStage = null;
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
        updateStage("/gamePickerScreen.fxml");
    }

    public void changeSceneIntoGameRecommendation(){
        updateStage("/gameRecommendationScreen.fxml");
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
        updateStage("/startScreen.fxml");
        primaryStage.show();
    }
}
