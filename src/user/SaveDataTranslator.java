package user;

/*
 * to create or read JSONs to/from
 * last updated 04/21/2021
 * Author(s) Ian Holder
 */

import org.json.JSONObject;
import resources.DataStorage;

import java.util.HashMap;

public class SaveDataTranslator implements SaveDataTranslatorInterface{
    //called on application close to wrap user data into a JSON to be passed to file storage
    public static void saveUserData(){
        //create a JSON of the user data
        HashMap<String,Integer> userGenreMap = MapAndListConverter.fromLinkListToMap(User.getUserGenres());
        HashMap<String,Integer> userTagsMap = MapAndListConverter.fromLinkListToMap(User.getUserTags());
        HashMap<Integer,String> userViewedGames = User.getViewedGames();
        //pass the data to be wrapped in a json
        JSONObject toBeStored = UserDataWrapper.toJSON(userGenreMap,userTagsMap,userViewedGames);
        //send the json to be written in a file
        DataStorage.saveFile(toBeStored);
    }

    //called on application start to take in a json from file and set the user data
    public static Boolean loadUserData(){
        JSONObject toBeRead = new JSONObject();
        //call the read method to hopefully return a json
        toBeRead = DataStorage.readFile();
        return UserDataUnwrapper.unwrap(toBeRead);
    }
}
