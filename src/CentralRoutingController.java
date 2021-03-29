package src;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

/**
 * the central routing controller to handle and routing information to and from the views
 * last updated 03/25/2021
 * Author(s) Ian Holder,
 */

public class CentralRoutingController {

//start Screen
    //checkBoxes
    public CheckBox actionBox;
    public CheckBox shooterBox;
    public CheckBox indieBox;
    public CheckBox casualBox;
    public CheckBox adventureBox;
    public CheckBox rpgBox;
    public CheckBox strategyBox;
    public CheckBox simulationBox;
    public CheckBox puzzleBox;
    public CheckBox arcadeBox;
    public CheckBox platformerBox;
    public CheckBox racingBox;
    public CheckBox sportsBox;
    public CheckBox massivelyMultiplayerBox;
    public CheckBox fightingBox;
    public CheckBox familyBox;
    public CheckBox boardGamesBox;
    public CheckBox educationalBox;
    public CheckBox cardBox;

    public Button doneButton;
    
    //game picker
    public Label gameTitle;
    public Button getRecommendationButton;
    public Button disLikeButton;
    public Button likeButton;
    public Button doNotKnowButton;

    public ImageView gameCoverArtImageView;

    //game recommendation
    public Label recommendationGameTitle;
    public ImageView recommendationGameCoverArt;
    public Button doneWithRecommendationButton;
    public ListView recommendationListView;

    //when the user clicks the done button pass the checkboxes to a model to handle it
    public void userHasClickedTheDoneButton(){
        StartScreenController.handleUserStartCheckBoxes(actionBox, indieBox, adventureBox, rpgBox, strategyBox,
                shooterBox,casualBox, simulationBox,puzzleBox,arcadeBox,platformerBox,racingBox,sportsBox,
                massivelyMultiplayerBox,fightingBox,familyBox,boardGamesBox,educationalBox,cardBox);
    }

}
