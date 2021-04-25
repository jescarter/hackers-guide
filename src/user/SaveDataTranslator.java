package user;

/*
 * to create or read JSONs to/from
 * last updated 04/24/2021
 * Author(s) Ian Holder
 */

import org.json.JSONArray;
import org.json.JSONObject;
import resources.DataStorage;
import resources.DataStorageIntf;
import resources.MockDataStorage;

import java.util.HashMap;
import java.util.Map;

public class SaveDataTranslator{
    private static DataStorageIntf dataStorage;
    //called on application close to wrap user data into a JSON to be passed to file storage
    public static void saveUserData(){
        //create a JSON of the user data
        HashMap<String,Integer> userGenreMap = fromLinkListToMap(User.getUserGenres());
        HashMap<String,Integer> userTagsMap = fromLinkListToMap(User.getUserTags());
        HashMap<String,String> userViewedGames = User.getViewedGames();
        //pass the data to be wrapped in a json
        JSONObject toBeStored = toJSON(userGenreMap,userTagsMap,userViewedGames);
        //send the json to be written in a file
        dataStorage.saveFile(toBeStored);
    }

    //called on application start to take in a json from file and set the user data
    public static User loadUserData(){
        JSONObject toRead = new JSONObject();
        //call the read method to hopefully return a json
        toRead = dataStorage.readFile();
        //pass the json to be parsed and return a new user object
        return unwrap(toRead);
    }

    private static HashMap<String,Integer> fromLinkListToMap(DoubledLinkList _inputList){
        HashMap<String,Integer> toReturn = new HashMap<>();
        if(!_inputList.empty()) {
            DoubledLinkList.Node current = _inputList.head;
            while (current != null) {
                toReturn.put(current.nodeTitle, current.preferenceValue);
                current = current.next;
            }
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
    private static User unwrap(JSONObject _saveData){
        User newUser = new User();
        if(!_saveData.isNull("userGenre")){
            HashMap<String,Integer> userGenres;
            HashMap<String,Integer> userTags;
            HashMap<String,String> userViewedGames;
            try {
                userGenres = jsonArrayToStrIntMap(_saveData.getJSONArray("userGenre"));
                userTags = jsonArrayToStrIntMap(_saveData.getJSONArray("userTags"));
                userViewedGames = jsonArrayToStrStrMap(_saveData.getJSONArray("viewedGames"));
                newUser.setUserGenres(fromMapToLinkList(userGenres));
                newUser.setUserTags(fromMapToLinkList(userTags));
                newUser.setViewedGames(userViewedGames);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return newUser;
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

    //testing method
    public static void test(){
        test1();
        User.clear();
        test2();
        User.clear();

    }

    private static void test1(){
        System.out.println("Test one");
        System.out.println("Testing for an empty user object, edge case");
        User.setViewedGames(new HashMap<>());
        User.setUserGenres(new DoubledLinkList());
        User.setUserTags(new DoubledLinkList());
        MockDataStorage test = new MockDataStorage();
        try {
            test.setDesiredSaveFileJSON(new JSONObject("{\"viewedGames\":[],\"userTags\":[],\"userGenre\":[]}"));
        }catch (Exception e){
            e.printStackTrace();
        }
        setDataStorage(test);
        saveUserData();
    }

    private static void test2(){
        System.out.println("Test two");
        System.out.println("Testing a user with 100 rated games, tags, and Genres, edge case");
        //populate the user with imposable large data
        HashMap<String,String> mockViewedGames = fillHashMap(10);
        DoubledLinkList mockedGenres = fillList(19);
        DoubledLinkList mockedTags = fillList(20);
        User.setUserGenres(mockedGenres);
        User.setUserTags(mockedTags);
        User.setViewedGames(mockViewedGames);

        MockDataStorage test = new MockDataStorage();
        try{
            test.setDesiredSaveFileJSON(new JSONObject());
        }catch (Exception e){
            e.printStackTrace();
        }
        setDataStorage(test);
        saveUserData();
    }

    //helper to fill a hash map with 100 elements
    private static HashMap<String,String> fillHashMap(int _numberOfElements){
        HashMap<String,String> toReturn = new HashMap<>();
        for(int i = 0; i <= _numberOfElements; i++){
            String tempKey = String.valueOf(i);
            String tempValue = String.valueOf(i);
            toReturn.put(tempKey, tempValue);
        }
        return toReturn;
    }
    //helper to fill Double Link lists with 100 elements
    private static DoubledLinkList fillList(int _numberOfElements){
        DoubledLinkList toReturn = new DoubledLinkList();
        for(int i = 0; i <= _numberOfElements; i++){
            String tempTitle = String.valueOf(i);
            toReturn.addElement(tempTitle,i);
        }
        return toReturn;
    }
    //================= SETTERS ===============
    public static void setDataStorage(DataStorageIntf _dataStorage){
        dataStorage = _dataStorage;
    }
}
