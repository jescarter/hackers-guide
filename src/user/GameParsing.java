package user;

/*
 * helper class that intakes the games from the user controller to update user data
 * last updated 04/20/2021
 * Author(s) Ian Holder, Jesse Carter
 */

import resources.Game;

public class GameParsing {
    public static void likedGame(Game _inputGame, int _incrementValue){
        //add all the genres of the game to the liked link list in the user
        for (String genre: _inputGame.getGenre()) {
            User.addGenre(genre,_incrementValue);
        }
        //add all the tags
        for (String tag: _inputGame.getTags()) {
            User.addTag(tag,_incrementValue);
        }
        //add to the viewed map
        User.addViewedGame(_inputGame.getGameID(), _inputGame.getTitle());
    }

    public static void disLikedGame(Game _inputGame, int _decrementValue){
        for (String tag:_inputGame.getTags()) {
            User.addTag(tag,_decrementValue);
        }
        User.addViewedGame(_inputGame.getGameID(), _inputGame.getTitle());
    }

    public static void addStartScreenSelections(String[] _userSelection, int _incrementValue){
        for (String temp:_userSelection) {
            User.addGenre(temp,_incrementValue);
        }
    }
}
