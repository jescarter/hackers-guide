package gameGenie;

/*
 * an application controller,that will handle scene transitions and loading in user data
 * last updated 04/27/2021
 * Author(s) Ian Holder,
 */

import javafx.scene.control.CheckBox;
import resources.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import resources.GameQueue;
import java.util.Objects;
import static gameGenie.GameController.getGameQueue;

public class GameGenieController {
    //the window is held by the game genie controller
    private Stage primaryStage;
    //the game genie controller to be a singleton holds reference to itself
    private static GameGenieController controller;
    //for the game picker screen gets info from the game queue
    private static GameQueue<Game> gameQueue;
    //view file names
    private static final String startScreenFile = "/startScreen.fxml";
    private static final String gamePickerFile = "/gamePickerScreen.fxml";
    private static final String gameRecommendationFile = "/gameRecommendationScreen.fxml";


    //on application start when the stage is passed figure out which scene to show if there is user data to load in
    protected void setPrimaryStage(Stage _stage){
        this.primaryStage = _stage;
        this.primaryStage.setTitle("Game Genie");
        //if there is no data loaded into the user at application start, show the start screen
        if(!UserController.userDataLoaded()) {
            updateStage(startScreenFile);
        }else{
            changeSceneIntoGamePicker();
        }
        this.primaryStage.show();
    }

    //will create a new scene from an inputted file name and update the stage
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
        updateStage(gamePickerFile);
    }

    //loading into the game recommendation screen
    public void changeSceneIntoGameRecommendation(){
        updateStage(gameRecommendationFile);
    }

    //make the GameGenieController a singleton
    public static GameGenieController getInstance(){
        if(controller == null){
            controller = new GameGenieController();
        }
        return controller;
    }

    //Start Screen

    //route from the start screen to the user controller to be evaluated
    public static void handleStartCheckBoxes(CheckBox[] _startCheckBoxes){
        //pass the check box array to be evaluated in the game parsing model
        UserController.handleCheckBoxes(_startCheckBoxes);
    }

    //Game Picker

    //when the scene is set to game picker make sure that there are values in the game queue
    public static Game getGamePickerGame(){
        //if there are no games in the queue then get more game, also populates on first call
        if(gameQueue == null || gameQueue.isEmpty()){
            gameQueue = getGameQueue();
        }
        return gameQueue.peek();
    }

    //pass the game from the top of the queue to the user controller to update user info
    public static void userLikedGame(){
        //send the top game to be parsed below
        UserController.liked(Objects.requireNonNull(gameQueue.poll()));
    }

    public static void userDislikedGame(){
        UserController.disliked(Objects.requireNonNull(gameQueue.poll()));
    }

    //just remove the top element from the game queue, for right now there is nothing to do with it
    public static void userDoesNotKnow(){
        gameQueue.poll();
    }

    //Game Recommendation

    //calls the game controller to get the one game for the game recommendation screen
    public static Game getRecommendation(){
        return GameController.getRecommendation();
    }

    //stage close

    //on application close tell the user controller that the application is closing
    public static void applicationClosing(){
        UserController.programClose();
    }

}
