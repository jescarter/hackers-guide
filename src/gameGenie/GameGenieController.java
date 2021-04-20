package gameGenie;

/**
 * an application controller,that will handle scene transitions and loading in user data
 * last updated 04/20/2021
 * Author(s) Ian Holder,
 */

import javafx.scene.control.CheckBox;
import model.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import resources.GameQueue;
import java.util.Objects;
import static gameGenie.GameController.getGameQueue;
import static gameGenie.UserController.userDataLoaded;

public class GameGenieController {
    private Stage primaryStage;
    private static GameGenieController controller = null;
    private static GameQueue<Game> gameQueue;

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

    public static void handleStartCheckBoxes(CheckBox[] _startCheckBoxes){
        UserController.handleCheckBoxes(_startCheckBoxes);
    }

    public static Game getGamePickerGame(){
        if(gameQueue == null || gameQueue.isEmpty()){
            gameQueue = getGameQueue();
        }
        return gameQueue.peek();
    }

    public static void userLikedGame(){
        UserController.Liked(Objects.requireNonNull(gameQueue.poll()));
    }

    public static void userDislikedGame(){
        UserController.Disliked(Objects.requireNonNull(gameQueue.poll()));
    }

    public static void userDoesNotKnow(){
        gameQueue.poll();
    }

    public static Game getRecommendation(){
        return GameController.getRecommendation();
    }

    public void setPrimaryStage(Stage _stage){
        this.primaryStage = _stage;
        primaryStage.setTitle("Game Genie");
        //if there is no data loaded into the user at application start
        if(!userDataLoaded()) {
            updateStage("/startScreen.fxml");
        }else{
            updateStage("/gamePickerScreen.fxml");
        }
        primaryStage.show();
    }

}
