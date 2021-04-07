package src.model;
/**
 * The user object, which will hold user selections and a list of previously viewed games.
 * last updated 04/06/2021
 * Author(s) Ian Holder, Jesse Carter
 */

import src.resources.DoubledLinkList;
import java.util.HashMap;

public class User {
    public static DoubledLinkList userGenres = new DoubledLinkList();
    public static DoubledLinkList userTags = new DoubledLinkList();
    public static HashMap<Integer, String> viewedGames = new HashMap<Integer, String>();

    public User() {
        this.setUserGenres(new DoubledLinkList());
        this.setUserTags(new DoubledLinkList());
        this.setViewedGames(new HashMap<Integer, String>());
    }

    public void addViewedGame(int _gameID, String _gameTitle) {
        //Add the viewed game to the user's list of previously viewed games.
        this.viewedGames.putIfAbsent(_gameID, _gameTitle);
    }

    public boolean wasViewed(int _gameID) {
        // Checks if the given game ID has been viewed by the user.
        return this.viewedGames.containsKey(_gameID);
    }

    //=================  GETTERS ===============
    public DoubledLinkList getUserGenres () {
        return this.userGenres;
    }

    public DoubledLinkList getUserTags() {
        return this.userTags;
    }

    public HashMap<Integer, String> getViewedGames() {
        return this.viewedGames;
    }

    //=================  Setters ===============
    public void setUserGenres(DoubledLinkList _genreList) {
        this.userGenres = _genreList;
    }

    public void setUserTags(DoubledLinkList _tags) {
        this.userTags = _tags;
    }

    public void setViewedGames (HashMap<Integer, String> _viewedGames) {
        this.viewedGames = _viewedGames;
    }

}
