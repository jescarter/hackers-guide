package user;

/*
 * helper class to take in a json and set user data
 * last updated 04/22/2021
 * Author(s) Ian Holder
 */

import org.json.JSONArray;
import org.json.JSONObject;
import resources.Util;

import java.util.HashMap;

//default is paket protected
class UserDataUnwrapper {
    //takes out the json arrays and populates the hash maps with them
    protected static boolean unwrap(JSONObject _saveData){
        if(!_saveData.isNull("userGenre")){
            HashMap<String,Integer> userGenres;
            HashMap<String,Integer> userTags;
            HashMap<Integer,String> userViewedGames;
            try {
                userGenres = jsonArrayToStrIntMap(_saveData.getJSONArray("userGenre"));
                userTags = jsonArrayToStrIntMap(_saveData.getJSONArray("userTags"));
                userViewedGames = jsonArrayToIntStrMap(_saveData.getJSONArray("viewedGames"));
                User.setUserGenres(Util.fromMapToLinkList(userGenres));
                User.setUserTags(Util.fromMapToLinkList(userTags));
                User.setViewedGames(userViewedGames);
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    private static HashMap<String,Integer> jsonArrayToStrIntMap(JSONArray _inputArray){
        HashMap<String,Integer> toBeReturned = new HashMap<>();
        try{
            for (int i = 0; i < _inputArray.length(); i++) {
                JSONObject temp = _inputArray.getJSONObject(i);
                toBeReturned.put(temp.getString("key"), temp.getInt("value"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return toBeReturned;
    }

    private static HashMap<Integer,String> jsonArrayToIntStrMap(JSONArray _inputArray){
        HashMap<Integer,String> toBeReturned = new HashMap<>();
        try{
            for (int i = 0; i < _inputArray.length(); i++) {
                JSONObject temp = _inputArray.getJSONObject(i);
                toBeReturned.put(temp.getInt("key"), temp.getString("value"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return toBeReturned;
    }
}
