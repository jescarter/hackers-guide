package viewLogic;

/*
 * A controller that is going to pass the start screen information to a model to handle it
 * last updated 03/25/2021
 * Author(s) Ian Holder,
 */

import gameGenie.GameGenieController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

public class StartScreenLogic {
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

    @FXML public void initialize(){
        this.actionBox.setText("Action");
        this.indieBox.setText("Indie");
        this.adventureBox.setText("Adventure");
        this.rpgBox.setText("RPG");
        this.strategyBox.setText("Strategy");
        this.shooterBox.setText("Shooter");
        this.casualBox.setText("Casual");
        this.simulationBox.setText("Simulation");
        this.puzzleBox.setText("Puzzle");
        this.arcadeBox.setText("Arcade");
        this.platformerBox.setText("Platformer");
        this.racingBox.setText("Racing");
        this.sportsBox.setText("Sports");
        this.massivelyMultiplayerBox.setText("Massively Multiplayer");
        this.fightingBox.setText("Fighting");
        this.familyBox.setText("Family");
        this.boardGamesBox.setText("Board Games");
        this.educationalBox.setText("Educational");
        this.cardBox.setText("Card");
    }


    @FXML
    private void userClickedDoneButton() {
        //pack all the checkboxes in order to the util genresArray
        CheckBox[] startCheckBoxes = new CheckBox[]{this.actionBox, this.indieBox, this.adventureBox, this.rpgBox,
                this.strategyBox, this.shooterBox, this.casualBox, this.simulationBox, this.puzzleBox, this.arcadeBox,
                this.platformerBox, this.racingBox, this.sportsBox, this.massivelyMultiplayerBox, this.fightingBox,
                this.familyBox, this.boardGamesBox, this.educationalBox, this.cardBox};
        String[] checkedValues = new String[19];
        for(int i = 0; i < startCheckBoxes.length; i++){
            if(startCheckBoxes[i].isSelected()){
                checkedValues[i] = startCheckBoxes[i].getText();
            }
        }
        GameGenieController.getInstance().handleStartCheckBoxes(checkedValues);
        GameGenieController.getInstance().changeSceneIntoGamePicker();
    }


}
