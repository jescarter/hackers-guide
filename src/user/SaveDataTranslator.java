package user;

/*
 * to create or read JSONs to/from
 * last updated 04/24/2021
 * Author(s) Ian Holder
 */

import org.json.JSONArray;
import org.json.JSONObject;
import resources.DataStorage;

import java.util.HashMap;
import java.util.Map;

public class SaveDataTranslator{
    //called on application close to wrap user data into a JSON to be passed to file storage
    public static void saveUserData(){
        //create a JSON of the user data
        HashMap<String,Integer> userGenreMap = fromLinkListToMap(User.getUserGenres());
        HashMap<String,Integer> userTagsMap = fromLinkListToMap(User.getUserTags());
        HashMap<String,String> userViewedGames = User.getViewedGames();
        //pass the data to be wrapped in a json
        JSONObject toBeStored = toJSON(userGenreMap,userTagsMap,userViewedGames);
        //send the json to be written in a file
        DataStorage.saveFile(toBeStored);
    }

    //called on application start to take in a json from file and set the user data
    public static Boolean loadUserData(){
        JSONObject toRead = new JSONObject();
        //call the read method to hopefully return a json
        toRead = DataStorage.readFile();
        //pass the json to be parsed and have it's data populate the user class
        return unwrap(toRead);
    }

    private static HashMap<String,Integer> fromLinkListToMap(DoubledLinkList _inputList){
        HashMap<String,Integer> toReturn = new HashMap<>();
        DoubledLinkList.Node current = _inputList.head;
        while(current != null){
            toReturn.put(current.nodeTitle, current.preferenceValue);
            current = current.next;
        }
        return toReturn;
    }

    private static DoubledLinkList fromMapToLinkList(HashMap<String,Integer> _inputMap){
        DoubledLinkList toBeReturned = new DoubledLinkList();
        for (Map.Entry<String, Integer> stringIntegerEntry : _inputMap.entrySet()) {
            toBeReturned.addElement(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
        }
        return toBeReturned;
    }

    //takes out the json arrays and populates the hash maps with them
    private static boolean unwrap(JSONObject _saveData){
        if(!_saveData.isNull("userGenre")){
            HashMap<String,Integer> userGenres;
            HashMap<String,Integer> userTags;
            HashMap<String,String> userViewedGames;
            try {
                userGenres = jsonArrayToStrIntMap(_saveData.getJSONArray("userGenre"));
                userTags = jsonArrayToStrIntMap(_saveData.getJSONArray("userTags"));
                userViewedGames = jsonArrayToStrStrMap(_saveData.getJSONArray("viewedGames"));
                User.setUserGenres(fromMapToLinkList(userGenres));
                User.setUserTags(fromMapToLinkList(userTags));
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
