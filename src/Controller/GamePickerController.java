package Controller;

import GameGenie.GameGenieController;
import Model.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import resources.GameQueue;
import resources.Load;

import static Model.User.*;

/**
 * Controller for the game picker fxml, that will show games
 * last updated 04/06/2021
 * Author(s) Ian Holder,
 */

public class GamePickerController {
    private Game currentGame;
    //TODO move to GameGenieController to stay in scope during scene transitions
    private GameQueue gameQueue;

    @FXML
    protected Label gameTitle;
    @FXML
    protected Button getRecommendationButton;
    @FXML
    protected Button disLikeButton;
    @FXML
    protected Button likeButton;
    @FXML
    protected Button doNotKnowButton;
    @FXML
    protected ImageView gameCoverArtImageView;

    @FXML public void initialize(){
        //gameQueue = Load.getGameQueue();
        getNextGame();
    }

    public void DislikeClicked(ActionEvent actionEvent) {
        for (String tag : currentGame.getTags()) {
            userTags.addElement(tag, -1);
        }
        getNextGame();
    }

    public void LikeClicked(ActionEvent actionEvent) {
        userGenres.addElement(currentGame.getGenre(), 1);
        for (String tag : currentGame.getTags()) {
            userTags.addElement(tag,1);
        }
        getNextGame();
    }

    public void DoNotKnowClicked(ActionEvent actionEvent){
        getNextGame();
    }

    public void setCurrentGame(Game _game){
        currentGame = _game;
        gameTitle.setText(currentGame.getTitle());
        try {
            Image image1 = new Image("File:" + currentGame.getCoverFilePath().toString());
            gameCoverArtImageView.setImage(image1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getNextGame(){
        if(gameQueue == null || gameQueue.isEmpty()){
            gameQueue = Load.getGameQueue();
        }
        setCurrentGame((Game)gameQueue.poll());
    }

    public void getRecommendationClicked(){
        GameGenieController.getInstance().changeSceneIntoGameRecommendation();
    }
}
