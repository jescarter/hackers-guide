package controller;

import game_genie.GameGenieController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

import static model.User.userGenres;

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
            userGenres.addElement("Action", 1);
        }
        if (this.indieBox.isSelected()) {
            userGenres.addElement("Indie", 1);
        }
        if (this.adventureBox.isSelected()) {
            userGenres.addElement("Adventure", 1);
        }
        if (this.rpgBox.isSelected()) {
            userGenres.addElement("RPG", 1);
        }
        if (this.strategyBox.isSelected()) {
            userGenres.addElement("Strategy", 1);
        }
        if (this.shooterBox.isSelected()) {
            userGenres.addElement("Shooter", 1);
        }
        if (this.casualBox.isSelected()) {
            userGenres.addElement("Casual", 1);
        }
        if (this.simulationBox.isSelected()) {
            userGenres.addElement("Simulation", 1);
        }
        if (this.puzzleBox.isSelected()) {
            userGenres.addElement("Puzzle", 1);
        }
        if (this.arcadeBox.isSelected()) {
            userGenres.addElement("Arcade", 1);
        }
        if (this.platformerBox.isSelected()) {
            userGenres.addElement("Platformer", 1);
        }
        if (this.racingBox.isSelected()) {
            userGenres.addElement("Racing", 1);
        }
        if (this.sportsBox.isSelected()) {
            userGenres.addElement("Sports", 1);
        }
        if (this.massivelyMultiplayerBox.isSelected()) {
            userGenres.addElement("Massively Multiplayer", 1);
        }
        if (this.fightingBox.isSelected()) {
            userGenres.addElement("Fighting", 1);
        }
        if (this.familyBox.isSelected()) {
            userGenres.addElement("Family", 1);
        }
        if (this.boardGamesBox.isSelected()) {
            userGenres.addElement("Board Games", 1);
        }
        if (this.educationalBox.isSelected()) {
            userGenres.addElement("Educational", 1);
        }
        if (this.cardBox.isSelected()) {
            userGenres.addElement("Card", 1);
        }
        GameGenieController.getInstance().changeSceneIntoGamePicker();
    }


}
