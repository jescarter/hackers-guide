package src;

import javafx.scene.control.CheckBox;

/**
 * A controller that is going to pass the start screen information to a model to handle it
 * last updated 03/25/2021
 * Author(s) Ian Holder,
 */

public class StartScreenController {
    public static void handleUserStartCheckBoxes(CheckBox _actionBox, CheckBox _indieBox, CheckBox _adventureBox,
                                                 CheckBox _rpgBox, CheckBox _strategyBox, CheckBox _shooterBox,
                                                 CheckBox _casualBox, CheckBox _simulationBox, CheckBox _puzzleBox,
                                                 CheckBox _arcadeBox, CheckBox _platformerBox, CheckBox _racingBox,
                                                 CheckBox _sportsBox, CheckBox _massivelyMultiplayerBox,
                                                 CheckBox _fightingBox, CheckBox _familyBox, CheckBox _boardGamesBox,
                                                 CheckBox _educationalBox, CheckBox _cardBox) {
        //a class that will handle the start check boxes to load into the user genera link list
        User.checkBoxHandler(_actionBox,_indieBox,_adventureBox,_rpgBox,_strategyBox,_shooterBox,_casualBox,
                _simulationBox,_puzzleBox,_arcadeBox, _platformerBox,_racingBox,_sportsBox,_massivelyMultiplayerBox,
                _fightingBox,_familyBox,_boardGamesBox, _educationalBox,_cardBox);

    }

}
