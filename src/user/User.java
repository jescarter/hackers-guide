package user;

/*
 * the user selection data
 * last updated 04/22/2021
 * Author(s) Ian Holder, Jesse Carter
 */

import java.util.HashMap;

public class User {
    private static DoubledLinkList userGenres = new DoubledLinkList();
    private static DoubledLinkList userTags = new DoubledLinkList();
    private static HashMap<String, String> viewedGames = new HashMap<>();

    //if the user data is empty return default values
    private static final String defaultGenre = "Action";
    private static final String defaultTag = "Cube";
    private static User User;

    public User(){

    }

    //for the viewed games map, written by Jesse
    protected static void addViewedGame(String _gameID, String _gameTitle) {
        //Add the viewed game to the user's list of previously viewed games.
        viewedGames.putIfAbsent(_gameID, _gameTitle);
    }

    public static boolean wasViewed(String _gameID) {
        // Checks if the given game ID has been viewed by the user.
        return viewedGames.containsKey(_gameID);
    }

    protected static void addGenre(String _genreName, int _inputValue){
        userGenres.addElement(_genreName,_inputValue);
    }

    protected static void addTag(String _tagName, int _inputValue){
        userTags.addElement(_tagName,_inputValue);
    }

    //================= GETTERS ===============
    //getting the user data to be put into data storage on application close
    public static DoubledLinkList getUserGenres(){
        return userGenres;
    }

    public static DoubledLinkList getUserTags(){
        return userTags;
    }

    public static HashMap<String,String> getViewedGames(){
        return viewedGames;
    }

    public static boolean hasGenres(){
        return !userGenres.empty();
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
    public static void setUser(User _user){
        User = _user;
    }

    //for reading in from a data storage
    public static void setUserGenres(DoubledLinkList _userGenres){
        userGenres = _userGenres;
    }

    public static void setUserTags(DoubledLinkList _userTags){
        userTags = _userTags;
    }

    public static void setViewedGames(HashMap<String,String> _viewedGames){
        viewedGames = _viewedGames;
    }

    public static void clear(){
        setUserTags(new DoubledLinkList());
        setUserGenres(new DoubledLinkList());
        setViewedGames(new HashMap<>());
    }
}
