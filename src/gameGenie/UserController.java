package gameGenie;

/*
 * controller to handle user selections from the views
 * last updated 04/21/2021
 * Author(s) Ian Holder,
 */

import resources.Game;
import User.SaveDataTranslator;
import User.User;

public class UserController {
    private static final int defaultLikeValue = 1;
    private static final int defaultDislikeValue = -1;

    //populate the user liked genres based on the selected check boxes from the start screen
    protected static void handleCheckBoxes(String[] _checkBoxArray){
        for (String temp:_checkBoxArray) {
            User.addGenre(temp,defaultLikeValue);
        }
    }

    //populate the user genres and tags, selected from the game picker screen
    protected static void Liked(Game _game){
        //add all the genres of the game to the liked link list in the user
        for (String genre: _game.getGenre()) {
            User.addGenre(genre,defaultLikeValue);
        }
        //add all the tags
        for (String tag: _game.getTags()) {
            User.addTag(tag,defaultLikeValue);
        }
        //add to the viewed map
        User.addViewedGame(_game.getGameID(), _game.getTitle());
    }

    protected static void Disliked(Game _game){
        for (String tag:_game.getTags()) {
            User.addTag(tag,defaultDislikeValue);
        }
        User.addViewedGame(_game.getGameID(), _game.getTitle());
    }

    //call the Save translator to load in save file
    protected static boolean userDataLoaded(){
        return SaveDataTranslator.loadUserData();
    }

    //on close request save the user data
    protected static void programClose() {
        SaveDataTranslator.saveUserData();
    }
}
