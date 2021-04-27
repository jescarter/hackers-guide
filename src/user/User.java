package user;

/*
 * the user selection data
 * last updated 04/22/2021
 * Author(s) Ian Holder, Jesse Carter
 */

import resources.UserHistoryIntf;

import java.util.HashMap;

public class User {
    private static User user;
    private UserHistoryIntf userHistory;

    //for the viewed games map, written by Jesse
    protected void addViewedGame(String _gameID, String _gameTitle) {
        //Add the viewed game to the user's list of previously viewed games.
        this.userHistory.addGame(_gameID, _gameTitle);
    }

    public boolean wasViewed(String _gameID) {
        // Checks if the given game ID has been viewed by the user.
        return this.userHistory.hasViewedGame(_gameID);
    }

    protected void addGenre(String _genreName, int _inputValue){
        this.userHistory.addGenre(_genreName,_inputValue);
    }

    protected void addTag(String _tagName, int _inputValue){
        this.userHistory.addTag(_tagName,_inputValue);
    }

    //================= GETTERS ===============
    public boolean isEmpty(){
        if(this.userHistory == null){
            return true;
        }
        return this.userHistory.isEmpty();
    }

    //return the most liked tag or genre
    public String getMostLikedGenre(){
        return this.userHistory.getTopGenre();
    }

    public String getMostLikedTag(){
        return this.userHistory.getTopTag();
    }

    public static User getInstance(){
        if(user == null){
            user = new User();
        }
        return user;
    }

    public UserHistoryIntf getUserHistory(){
        return this.userHistory;
    }

    //================= SETTERS ===============
    public void setUserHistory(UserHistoryIntf _inputHistory){
        this.userHistory = _inputHistory;
    }

    public void clear(){
        this.userHistory = null;
    }
}
