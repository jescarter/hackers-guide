package user;

/*
 * helper class to take in hash maps returning a json
 * last updated 04/22/2021
 * Author(s) Ian Holder, Jesse Carter
 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

class UserDataWrapper {
    //protected to only be acceded by the save data translator
    protected static JSONObject toJSON(HashMap<String,Integer> _userGenre, HashMap<String,Integer> _userTags,
                                       HashMap<String,String> _userViewedGames){
        //take the hash maps and turn into Json arrays
        JSONArray userGenre = fromStringIntMap(_userGenre);
        JSONArray userTags = fromStringIntMap(_userTags);
        JSONArray userViewedGames = fromStringStringMap(_userViewedGames);
        //put the json arrays into a json object
        JSONObject toBeReturned = new JSONObject();
        try{
            toBeReturned.put("userGenre", userGenre);
            toBeReturned.put("userTags", userTags);
            toBeReturned.put("viewedGames",userViewedGames);
        }catch (Exception e){
            e.printStackTrace();
        }
        return toBeReturned;
    }

    private static JSONArray fromStringIntMap(HashMap<String,Integer> _inputMap){
        JSONArray toBeReturned = new JSONArray();
        //using an enhanced for loop to take elements out of the map put into a object -> json array
        for (Map.Entry<String, Integer> stringIntegerEntry: _inputMap.entrySet()) {
            JSONObject temp = new JSONObject();
            try{
                temp.put("key", stringIntegerEntry.getKey());
                temp.put("value", stringIntegerEntry.getValue());
                toBeReturned.put(temp);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return toBeReturned;
    }

    private static JSONArray fromStringStringMap(HashMap<String,String> _inputMap){
        JSONArray toBeReturned = new JSONArray();
        for(Map.Entry<String,String> integerStringEntry: _inputMap.entrySet()){
            JSONObject temp = new JSONObject();
            try{
                temp.put("key", integerStringEntry.getKey());
                temp.put("value", integerStringEntry.getValue());
                toBeReturned.put(temp);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return toBeReturned;
    }
}
