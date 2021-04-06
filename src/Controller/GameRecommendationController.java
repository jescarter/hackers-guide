package Controller;

import GameGenie.GameGenieController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class GameRecommendationController {

    public Label recommendationGameTitle;
    public ImageView recommendationGameCoverArt;
    public ListView recommendationListView;
    public Button doneWithRecommendationButton;

    @FXML public void initialize(){

    }

    public void doneClicked(){
        GameGenieController.getInstance().changeSceneIntoGamePicker();
    }
}
