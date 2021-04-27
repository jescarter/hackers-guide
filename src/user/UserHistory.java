package user;

/*
 * the user data object to pack all the info for data handling
 * last updated 04/27/2021
 * Author(s) Ian Holder,
 */

import resources.UserHistoryIntf;
import java.util.HashMap;
import java.util.Map;

public class UserHistory implements UserHistoryIntf {
    private DoubledLinkList userGenres;
    private DoubledLinkList userTags;
    private HashMap<String, String> viewedGames;

    @Override
    public void addGenre(String _genre, int _preferenceMod) {
        this.userGenres.addElement(_genre, _preferenceMod);
    }

    @Override
    public void addTag(String _tag, int _preferenceMod) {
        this.userTags.addElement(_tag, _preferenceMod);
    }

    @Override
    public void addGame(String _gameTitle, String _gameID) {
        this.viewedGames.put(_gameTitle, _gameID);
    }

    @Override
    public boolean hasViewedGame(String _gameID) {
        return this.viewedGames.containsKey(_gameID);
    }

    @Override
    public boolean isEmpty() {
        if(this.userGenres == null){
            return false;
        }else {
            if (this.userGenres.empty() && this.userTags.empty()) {
                return this.viewedGames.isEmpty();
            }
        }
        return false;
    }

    @Override
    public boolean isEqual(UserHistoryIntf _inputObj) {
        if (_inputObj.isEmpty() && this.isEmpty()) {
            return true;
        }
        if (_inputObj.getUserGenres().equals(this.getUserGenres()) && _inputObj.getUserTags().equals(this.getUserTags())) {
            return _inputObj.getViewedGames().equals(this.viewedGames);
        }
        return false;
    }

    //in this object to use doubled link list but return and set hash maps
    private DoubledLinkList fromMapToLinkList(HashMap<String, Integer> _inputMap) {
        DoubledLinkList toBeReturned = new DoubledLinkList();
        for (Map.Entry<String, Integer> stringIntegerEntry : _inputMap.entrySet()) {
            toBeReturned.addElement(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
        }
        return toBeReturned;
    }

    private HashMap<String, Integer> fromLinkListToMap(DoubledLinkList _inputList) {
        HashMap<String, Integer> toReturn = new HashMap<>();
        if (_inputList == null) {
            return toReturn;
        } else {
            DoubledLinkList.Node current = _inputList.head;
            while (current != null) {
                toReturn.put(current.nodeTitle, current.preferenceValue);
                current = current.next;
            }
            return toReturn;
        }
    }

    //================= GETTERS ===============
    @Override
    public HashMap<String, Integer> getUserGenres() {
        return fromLinkListToMap(this.userGenres);
    }

    @Override
    public HashMap<String, Integer> getUserTags() {
        return fromLinkListToMap(this.userTags);
    }

    @Override
    public HashMap<String, String> getViewedGames() {
        return this.viewedGames;
    }

    @Override
    public String getTopGenre() {
        return this.userGenres.greatestValue();
    }

    @Override
    public String getTopTag() {
        return this.userTags.greatestValue();
    }

    //================= SETTERS ===============
    @Override
    public void setUserGenres(HashMap<String, Integer> _inputGenres) {
        this.userGenres = fromMapToLinkList(_inputGenres);
    }

    @Override
    public void setUserTags(HashMap<String, Integer> _inputTags) {
        this.userTags = fromMapToLinkList(_inputTags);
    }

    @Override
    public void setViewedGames(HashMap<String, String> _inputMap) {
        this.viewedGames = _inputMap;
    }
}
