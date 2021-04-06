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
            userGenres.addNode("Action");
        }
        if (this.indieBox.isSelected()) {
            userGenres.addNode("Indie");
        }
        if (this.adventureBox.isSelected()) {
            userGenres.addNode("Adventure");
        }
        if (this.rpgBox.isSelected()) {
            userGenres.addNode("RPG");
        }
        if (this.strategyBox.isSelected()) {
            userGenres.addNode("Strategy");
        }
        if (this.shooterBox.isSelected()) {
            userGenres.addNode("Shooter");
        }
        if (this.casualBox.isSelected()) {
            userGenres.addNode("Casual");
        }
        if (this.simulationBox.isSelected()) {
            userGenres.addNode("Simulation");
        }
        if (this.puzzleBox.isSelected()) {
            userGenres.addNode("Puzzle");
        }
        if (this.arcadeBox.isSelected()) {
            userGenres.addNode("Arcade");
        }
        if (this.platformerBox.isSelected()) {
            userGenres.addNode("Platformer");
        }
        if (this.racingBox.isSelected()) {
            userGenres.addNode("Racing");
        }
        if (this.sportsBox.isSelected()) {
            userGenres.addNode("Sports");
        }
        if (this.massivelyMultiplayerBox.isSelected()) {
            userGenres.addNode("Massively Multiplayer");
        }
        if (this.fightingBox.isSelected()) {
            userGenres.addNode("Fighting");
        }
        if (this.familyBox.isSelected()) {
            userGenres.addNode("Family");
        }
        if (this.boardGamesBox.isSelected()) {
            userGenres.addNode("Board Games");
        }
        if (this.educationalBox.isSelected()) {
            userGenres.addNode("Educational");
        }
        if (this.cardBox.isSelected()) {
            userGenres.addNode("Card");
        }
        GameGenieController.getInstance().changeSceneIntoGamePicker();
    }


}
