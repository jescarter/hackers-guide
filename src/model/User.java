package model;

/**
 * the user selection data
 * last updated 04/20/2021
 * Author(s) Ian Holder, Jesse Carter
 */

import resources.DoubledLinkList;
import java.util.HashMap;

public class User {
    private static DoubledLinkList userGenres = new DoubledLinkList();
    private static DoubledLinkList userTags = new DoubledLinkList();
    private static HashMap<Integer, String> viewedGames = new HashMap<>();

    //if the user data is empty return default values
    private static final String defaultGenre = "Action";
    private static final String defaultTag = "Cube";
    private static final int defaultLikeValue = 1;
    private static final int defaultDislikeValue = -1;

    //for the viewed games map
    public static void addViewedGame(int _gameID, String _gameTitle) {
        //Add the viewed game to the user's list of previously viewed games.
        viewedGames.putIfAbsent(_gameID, _gameTitle);
    }

    public static boolean wasViewed(int _gameID) {
        // Checks if the given game ID has been viewed by the user.
        return viewedGames.containsKey(_gameID);
    }

    //for the user genres link list
    public static void addGenreLike(String _genreName){
        userGenres.addElement(_genreName, defaultLikeValue);
    }

    public static void addGenreDisliked(String _genreName){
        userGenres.addElement(_genreName, defaultDislikeValue);
    }
    //for the user tags link list
    public static void addTagLiked(String _tagName){
        userTags.addElement(_tagName, defaultLikeValue);
    }

    public static void addTagDisliked(String _tagName){
        userTags.addElement(_tagName, defaultDislikeValue);
    }

    //================= GETTERS ===============
    //getting the user data to be put into data storage on application close
    public static DoubledLinkList getUserGenres(){
        return userGenres;
    }

    public static DoubledLinkList getUserTags(){
        return userTags;
    }

    public static HashMap<Integer,String> getViewedGames(){
        return viewedGames;
    }

    //return the most liked tag or genre
    public static String getMostLikedGenre(){
        if(userGenres.greatestValue() == null){
            return defaultGenre;
        }
        return userGenres.greatestValue();
    }

    public static String getMostLikedTag(){
        if(userTags.greatestValue() == null){
            return defaultTag;
        }
        return userTags.greatestValue();
    }

    //================= SETTERS ===============
    //for reading in from a data storage
    public static void setUserGenres(DoubledLinkList _userGenres){
        userGenres = _userGenres;
    }

    public static void setUserTags(DoubledLinkList _userTags){
        userTags = _userTags;
    }

    public static void setViewedGames(HashMap<Integer,String> _viewedGames){
        viewedGames = _viewedGames;
    }

}
