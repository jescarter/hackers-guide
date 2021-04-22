package user;

/*
 * to create or read JSONs to/from
 * last updated 04/21/2021
 * Author(s) Ian Holder
 */

import org.json.JSONArray;
import org.json.JSONObject;
import resources.Util;
import java.util.HashMap;
import java.util.Map;

public class SaveDataTranslator implements SaveDataTranslatorInterface{
    //called on application close to wrap user data into a JSON to be passed to file storage
    public static void saveUserData(){
        //create a JSON of the user data
        HashMap<String,Integer> userGenreMap = Util.fromLinkListToMap(User.getUserGenres());
        HashMap<String,Integer> userTagsMap = Util.fromLinkListToMap(User.getUserTags());
        HashMap<Integer,String> userViewedGames = User.getViewedGames();
        JSONObject toBeStored = UserDataWrapper.toJSON(userGenreMap,userTagsMap,userViewedGames);

    }

    //called on application start to take in a json from file and set the user data
    public static Boolean loadUserData(){
        JSONObject toBeRead = new JSONObject();
        return UserDataUnwrapper.unwrap(toBeRead);
    }
}
