package controller;

import game_genie.GameGenieController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import static model.User.addGenreLike;

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
            addGenreLike("Action");
        }
        if (this.indieBox.isSelected()) {
            addGenreLike("Indie");
        }
        if (this.adventureBox.isSelected()) {
            addGenreLike("Adventure");
        }
        if (this.rpgBox.isSelected()) {
            addGenreLike("RPG");
        }
        if (this.strategyBox.isSelected()) {
            addGenreLike("Strategy");
        }
        if (this.shooterBox.isSelected()) {
            addGenreLike("Shooter");
        }
        if (this.casualBox.isSelected()) {
            addGenreLike("Casual");
        }
        if (this.simulationBox.isSelected()) {
            addGenreLike("Simulation");
        }
        if (this.puzzleBox.isSelected()) {
            addGenreLike("Puzzle");
        }
        if (this.arcadeBox.isSelected()) {
            addGenreLike("Arcade");
        }
        if (this.platformerBox.isSelected()) {
            addGenreLike("Platformer");
        }
        if (this.racingBox.isSelected()) {
            addGenreLike("Racing");
        }
        if (this.sportsBox.isSelected()) {
            addGenreLike("Sports");
        }
        if (this.massivelyMultiplayerBox.isSelected()) {
            addGenreLike("Massively Multiplayer");
        }
        if (this.fightingBox.isSelected()) {
            addGenreLike("Fighting");
        }
        if (this.familyBox.isSelected()) {
            addGenreLike("Family");
        }
        if (this.boardGamesBox.isSelected()) {
            addGenreLike("Board Games");
        }
        if (this.educationalBox.isSelected()) {
            addGenreLike("Educational");
        }
        if (this.cardBox.isSelected()) {
            addGenreLike("Card");
        }
        GameGenieController.getInstance().changeSceneIntoGamePicker();
    }


}
