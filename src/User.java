package src;

import javafx.scene.control.CheckBox;

/**
 * the user to hold user selections and
 * last updated 03/28/2021
 * Author(s) Ian Holder,
 */

public class User {
    protected static DoubledLinkList userGenres = new DoubledLinkList();
    protected static DoubledLinkList likedTags = new DoubledLinkList();
    protected static DoubledLinkList dislikedTags = new DoubledLinkList();

    public static void checkBoxHandler(CheckBox _actionBox, CheckBox _indieBox, CheckBox _adventureBox, CheckBox _rpgBox,
                                       CheckBox _strategyBox, CheckBox _shooterBox, CheckBox _casualBox,
                                       CheckBox _simulationBox, CheckBox _puzzleBox, CheckBox _arcadeBox,
                                       CheckBox _platformerBox, CheckBox _racingBox, CheckBox _sportsBox,
                                       CheckBox _massivelyMultiplayerBox, CheckBox _fightingBox, CheckBox _familyBox,
                                       CheckBox _boardGamesBox, CheckBox _educationalBox, CheckBox _cardBox) {
        if(_actionBox.isSelected()){
            userGenres.addNode("Action");
        }
        if(_indieBox.isSelected()){
            userGenres.addNode("Indie");
        }
        if(_adventureBox.isSelected()){
            userGenres.addNode("Adventure");
        }
        if(_rpgBox.isSelected()){
            userGenres.addNode("RPG");
        }
        if(_strategyBox.isSelected()){
            userGenres.addNode("Strategy");
        }
        if(_shooterBox.isSelected()){
            userGenres.addNode("Shooter");
        }
        if(_casualBox.isSelected()){
            userGenres.addNode("Casual");
        }
        if(_simulationBox.isSelected()){
            userGenres.addNode("Simulation");
        }
        if(_puzzleBox.isSelected()){
            userGenres.addNode("Puzzle");
        }
        if(_arcadeBox.isSelected()){
            userGenres.addNode("Arcade");
        }
        if(_platformerBox.isSelected()){
            userGenres.addNode("Platformer");
        }
        if(_racingBox.isSelected()){
            userGenres.addNode("Racing");
        }
        if(_sportsBox.isSelected()){
            userGenres.addNode("Sports");
        }
        if(_massivelyMultiplayerBox.isSelected()){
            userGenres.addNode("Massively Multiplayer");
        }
        if(_fightingBox.isSelected()){
            userGenres.addNode("Fighting");
        }
        if(_familyBox.isSelected()){
            userGenres.addNode("Family");
        }
        if(_boardGamesBox.isSelected()){
            userGenres.addNode("Board Games");
        }
        if(_educationalBox.isSelected()){
            userGenres.addNode("Educational");
        }
        if(_cardBox.isSelected()){
            userGenres.addNode("Card");
        }
    }

    public void user(){

    }
}
