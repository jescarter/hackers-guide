package user;

/*
 * the user selection data
 * last updated 04/29/2021
 * Author(s) Ian Holder, Jesse Carter
 */

import javafx.scene.control.CheckBox;
import resources.Game;

public class User {
    private static User user;
    private UserHistoryIntf userHistory;

    //take the checkboxes from the views and parce
    public static void addStartScreenSelections(CheckBox[] _userSelection, int _incrementValue){
        for (CheckBox temp:_userSelection) {
            if(temp.isSelected()) {
                //the text of the check boxes are set in the view on initialization
                User.getInstance().addGenre(temp.getText(), _incrementValue);
            }
        }
    }

    //all it needs to know is the game and how much to mod it's accosted values
    public static void parseGame(Game _inputGame, int _incrementValue){
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

    //make the user a singleton
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
}
