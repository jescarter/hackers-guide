package Controller;

import GameGenie.GameGenieController;
import Model.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import resources.Load;

/**
 * Controller for the game recommendation fxml, that will show games
 * last updated 04/06/2021
 * Author(s) Ian Holder,
 */

public class GameRecommendationController {
    private Game currentGame;

    public Label recommendationGameTitle;
    public ImageView recommendationGameCoverArt;
    public ListView recommendationListView;
    public Button doneWithRecommendationButton;

    @FXML public void initialize(){
        currentGame = Load.getRecommendation();
        recommendationGameTitle.setText(currentGame.getTitle());
        recommendationGameCoverArt.setImage(new Image("File:" + currentGame.getCoverFilePath().toString()));
        //recommendationListView.setItems();
    }

    public void doneClicked(){
        GameGenieController.getInstance().changeSceneIntoGamePicker();
    }
}
