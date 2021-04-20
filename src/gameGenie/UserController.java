package gameGenie;

/**
 * controller to handle user selections from the views
 * last updated 04/20/2021
 * Author(s) Ian Holder,
 */

import javafx.scene.control.CheckBox;
import model.Game;
import model.User;
import resources.Util;

public class UserController {
    public static void handleCheckBoxes(CheckBox[] _checkBoxArray){
        for(int i = 0; i < _checkBoxArray.length; i++){
            if(_checkBoxArray[i].isSelected()){
                //the checkboxes should be in order according to the util array
                User.addGenreLike(Util.genresArray[i]);
            }
        }
    }

    public static void Liked(Game _game){
        //add all the genres of the game to the liked link list in the user
        for (String genre: _game.getGenre()) {
            User.addGenreLike(genre);
        }
        //add all the tags
        for (String tag: _game.getTags()) {
            User.addTagLiked(tag);
        }
        //add to the viewed map
        User.addViewedGame(_game.getGameID(), _game.getTitle());
    }

    public static void Disliked(Game _game){
        for (String tag:_game.getTags()) {
            User.addTagDisliked(tag);
        }
        User.addViewedGame(_game.getGameID(), _game.getTitle());
    }
    //TODO that will return if there was any data to the user class/object and load said data
    public static boolean userDataLoaded(){
        return false;
    }

    //on close request save the user data
    public static void programClose() {

    }
}
