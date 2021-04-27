package resources;

/*
 * to create or read JSONs to/from
 * last updated 04/27/2021
 * Author(s) Ian Holder
 */

import org.json.JSONArray;
import org.json.JSONObject;
import user.UserHistory;

import java.util.HashMap;
import java.util.Map;

public class SaveDataTranslator{
    //set what data storage is going to be used
    private static DataStorageIntf dataStorage;
    //called on application close to wrap user data into a JSON to be passed to file storage
    public static void saveUserData(UserHistoryIntf _dataToSave){
        //create a JSON of the user data
        HashMap<String,Integer> userGenreMap = _dataToSave.getUserGenres();
        HashMap<String,Integer> userTagsMap = _dataToSave.getUserTags();
        HashMap<String,String> userViewedGames = _dataToSave.getViewedGames();
        //pass the data to be wrapped in a json
        JSONObject toBeStored = toJSON(userGenreMap,userTagsMap,userViewedGames);
        //send the json to be written in a file
        dataStorage.saveFile(toBeStored);
    }

    //called on application start to take in a json from file and set the user data
    public static UserHistoryIntf loadUserData(){
        JSONObject toRead = new JSONObject();
        //call the read method to hopefully return a json
        toRead = dataStorage.readFile();
        if(!toRead.toString().isEmpty()) {
            //pass the json to be parsed and return a new user history object
            return unwrap(toRead);
        }else{
            return new UserHistory();
        }
    }

    //takes out the json arrays and populates the hash maps with them
    private static UserHistoryIntf unwrap(JSONObject _saveData){
        UserHistoryIntf newUser = new UserHistory();
        //check that there is data in the json
        if(!_saveData.isNull("userGenre")){
            //created the collections that will populate the user history
            HashMap<String,Integer> userGenres;
            HashMap<String,Integer> userTags;
            HashMap<String,String> userViewedGames;
            //use try catch to handle possible json exception
            try {
                userGenres = jsonArrayToStrIntMap(_saveData.getJSONArray("userGenre"));
                userTags = jsonArrayToStrIntMap(_saveData.getJSONArray("userTags"));
                userViewedGames = jsonArrayToStrStrMap(_saveData.getJSONArray("viewedGames"));
                //populate the user history
                newUser.setUserGenres(userGenres);
                newUser.setUserTags(userTags);
                newUser.setViewedGames(userViewedGames);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //return the user history to be sent to the user controller
        return newUser;
    }

    //helpers

    //used in loading
    private static HashMap<String,Integer> jsonArrayToStrIntMap(JSONArray _inputArray){
        HashMap<String,Integer> toBeReturned = new HashMap<>();
        //pull the ordered pairs wrapped in json object into the hash map
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
        //pull the ordered pairs wrapped in json object into the hash map
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

    //used in saving
    private static JSONObject toJSON(HashMap<String,Integer> _userGenre, HashMap<String,Integer> _userTags,
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
        if(_inputMap == null){
            return toBeReturned;
        }else {
            for (Map.Entry<String, String> integerStringEntry : _inputMap.entrySet()) {
                JSONObject temp = new JSONObject();
                try {
                    temp.put("key", integerStringEntry.getKey());
                    temp.put("value", integerStringEntry.getValue());
                    toBeReturned.put(temp);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return toBeReturned;
    }

    //================= SETTERS ===============
    //set what the data storage being used is
    public static void setDataStorage(DataStorageIntf _dataStorage){
        dataStorage = _dataStorage;
    }
}
