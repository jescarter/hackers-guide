package user;

/*
 * helper class to take in a json and set user data
 * last updated 04/22/2021
 * Author(s) Ian Holder
 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

//default is packet protected
class UserDataUnwrapper {
    //takes out the json arrays and populates the hash maps with them
    protected static boolean unwrap(JSONObject _saveData){
        if(!_saveData.isNull("userGenre")){
            HashMap<String,Integer> userGenres;
            HashMap<String,Integer> userTags;
            HashMap<String,String> userViewedGames;
            try {
                userGenres = jsonArrayToStrIntMap(_saveData.getJSONArray("userGenre"));
                userTags = jsonArrayToStrIntMap(_saveData.getJSONArray("userTags"));
                userViewedGames = jsonArrayToStrStrMap(_saveData.getJSONArray("viewedGames"));
                User.setUserGenres(MapAndListConverter.fromMapToLinkList(userGenres));
                User.setUserTags(MapAndListConverter.fromMapToLinkList(userTags));
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

    private static HashMap<String,String> jsonArrayToStrStrMap(JSONArray _inputArray){
        HashMap<String,String> toBeReturned = new HashMap<>();
        try{
            for (int i = 0; i < _inputArray.length(); i++) {
                JSONObject temp = _inputArray.getJSONObject(i);
                toBeReturned.put(temp.getString("key"), temp.getString("value"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return toBeReturned;
    }
}
