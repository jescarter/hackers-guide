package viewLogic;

/*
 * Controller for the game picker fxml, that will show games
 * last updated 04/08/2021
 * Author(s) Ian Holder,
 */

import gameGenie.GameGenieController;
import resources.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GamePickerLogic {
    private Game currentGame;

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
        getNextGame();
    }

    public void DislikeClicked(ActionEvent actionEvent) {
        GameGenieController.userDislikedGame();
        getNextGame();
    }

    public void LikeClicked(ActionEvent actionEvent) {
        GameGenieController.userLikedGame();
        getNextGame();
    }

    public void DoNotKnowClicked(ActionEvent actionEvent){
        GameGenieController.userDoesNotKnow();
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
        setCurrentGame(GameGenieController.getGamePickerGame());
    }

    public void getRecommendationClicked(){
        GameGenieController.getInstance().changeSceneIntoGameRecommendation();
    }
}
