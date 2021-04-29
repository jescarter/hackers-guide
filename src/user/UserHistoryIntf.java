package user;

/*
  an interface for the user history object for greater modularity, but it is to specific just to get it to work
  last updated 04/28/2021
  Author(s) Ian Holder,
 */

import java.util.HashMap;

public interface UserHistoryIntf {
    public void setUserGenres(HashMap<String,Integer> _inputGenres);
    public void setUserTags(HashMap<String,Integer> _inputTags);
    public void setViewedGames(HashMap<String,String> _inputMap);

    public HashMap<String,Integer> getUserGenres();
    public HashMap<String,Integer> getUserTags();
    public HashMap<String,String> getViewedGames();

    public String getTopGenre();
    public String getTopTag();

    public void addGenre(String _genre, int _preferenceMod);
    public void addTag(String _tag, int _preferenceMod);
    public void addGame(String _gameTitle, String _gameID);

    public boolean hasViewedGame(String _gameID);

    public boolean isEmpty();

    public boolean isEqual(UserHistoryIntf _inputObj);
}
