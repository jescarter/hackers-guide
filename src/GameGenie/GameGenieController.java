package GameGenie;

import Model.Game;
import Model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import resources.GameQueue;
import resources.Load;

/**
 * an application controller,that will handle scene transitions and loading in user data
 * last updated 04/02/2021
 * Author(s) Ian Holder,
 */

public class GameGenieController {
    private Stage primaryStage;
    private static GameGenieController controller = null;
    private static GameQueue gameQueue;

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

    //loading into the game recommendation screen
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

    public static Game getGamePickerGame(){
        if(gameQueue == null || gameQueue.isEmpty()){
            gameQueue = Load.getGameQueue();
        }
        return (Game)gameQueue.poll();
    }

    public void setPrimaryStage(Stage _stage){
        this.primaryStage = _stage;
        primaryStage.setTitle("Game Genie");
        //if there is no data loaded into the user at application start
        if(!Load.userDataLoaded()) {
            updateStage("/startScreen.fxml");
        }else{
            updateStage("/gamePickerScreen.fxml");
        }
        primaryStage.show();
    }
}
