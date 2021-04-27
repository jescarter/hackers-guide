package user;

/*
 * helper class that intakes the games from the user controller to update user data
 * last updated 04/27/2021
 * Author(s) Ian Holder, Jesse Carter
 */

import javafx.scene.control.CheckBox;
import resources.Game;

public class GameParsing {
    public static void likedGame(Game _inputGame, int _incrementValue){
        //add all the genres of the game to the liked link list in the user
        for (String genre: _inputGame.getGenre()) {
            User.getInstance().addGenre(genre,_incrementValue);
        }
        //add all the tags
        for (String tag: _inputGame.getTags()) {
            User.getInstance().addTag(tag,_incrementValue);
        }
        //add to the viewed map
        User.getInstance().addViewedGame(_inputGame.getGameID(), _inputGame.getTitle());
    }

    public static void disLikedGame(Game _inputGame, int _decrementValue){
        //if the user did not like a game then only modify the tags and add to the viewed games map
        for (String tag:_inputGame.getTags()) {
            User.getInstance().addTag(tag,_decrementValue);
        }
        User.getInstance().addViewedGame(_inputGame.getGameID(), _inputGame.getTitle());
    }

    public static void addStartScreenSelections(CheckBox[] _userSelection, int _incrementValue){
        for (CheckBox temp:_userSelection) {
            if(temp.isSelected()) {
                //the text of the check boxes are set in the view on initialization
                User.getInstance().addGenre(temp.getText(), _incrementValue);
            }
        }
    }
}
