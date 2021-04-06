package Controller;

import GameGenie.GameGenieController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import static Model.User.userGenres;

/**
 * A controller that is going to pass the start screen information to a model to handle it
 * last updated 03/25/2021
 * Author(s) Ian Holder,
 */

public class StartScreenController {
    @FXML
    private CheckBox actionBox;
    @FXML
    private CheckBox shooterBox;
    @FXML
    private CheckBox indieBox;
    @FXML
    private CheckBox casualBox;
    @FXML
    private CheckBox adventureBox;
    @FXML
    private CheckBox rpgBox;
    @FXML
    private CheckBox strategyBox;
    @FXML
    private CheckBox simulationBox;
    @FXML
    private CheckBox puzzleBox;
    @FXML
    private CheckBox arcadeBox;
    @FXML
    private CheckBox platformerBox;
    @FXML
    private CheckBox racingBox;
    @FXML
    private CheckBox sportsBox;
    @FXML
    private CheckBox massivelyMultiplayerBox;
    @FXML
    private CheckBox fightingBox;
    @FXML
    private CheckBox familyBox;
    @FXML
    private CheckBox boardGamesBox;
    @FXML
    private CheckBox educationalBox;
    @FXML
    private CheckBox cardBox;

    public Button doneButton;


    @FXML void handleUserStartCheckBoxes() {
        if (this.actionBox.isSelected()) {
            userGenres.addString("Action", 1);
        }
        if (this.indieBox.isSelected()) {
            userGenres.addString("Indie", 1);
        }
        if (this.adventureBox.isSelected()) {
            userGenres.addString("Adventure", 1);
        }
        if (this.rpgBox.isSelected()) {
            userGenres.addString("RPG", 1);
        }
        if (this.strategyBox.isSelected()) {
            userGenres.addString("Strategy", 1);
        }
        if (this.shooterBox.isSelected()) {
            userGenres.addString("Shooter", 1);
        }
        if (this.casualBox.isSelected()) {
            userGenres.addString("Casual", 1);
        }
        if (this.simulationBox.isSelected()) {
            userGenres.addString("Simulation", 1);
        }
        if (this.puzzleBox.isSelected()) {
            userGenres.addString("Puzzle", 1);
        }
        if (this.arcadeBox.isSelected()) {
            userGenres.addString("Arcade", 1);
        }
        if (this.platformerBox.isSelected()) {
            userGenres.addString("Platformer", 1);
        }
        if (this.racingBox.isSelected()) {
            userGenres.addString("Racing", 1);
        }
        if (this.sportsBox.isSelected()) {
            userGenres.addString("Sports", 1);
        }
        if (this.massivelyMultiplayerBox.isSelected()) {
            userGenres.addString("Massively Multiplayer", 1);
        }
        if (this.fightingBox.isSelected()) {
            userGenres.addString("Fighting", 1);
        }
        if (this.familyBox.isSelected()) {
            userGenres.addString("Family", 1);
        }
        if (this.boardGamesBox.isSelected()) {
            userGenres.addString("Board Games", 1);
        }
        if (this.educationalBox.isSelected()) {
            userGenres.addString("Educational", 1);
        }
        if (this.cardBox.isSelected()) {
            userGenres.addString("Card", 1);
        }
        GameGenieController.getInstance().changeSceneIntoGamePicker();
    }


}
