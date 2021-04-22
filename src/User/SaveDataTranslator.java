package User;

/*
 * to create or read JSONs to/from
 * last updated 04/21/2021
 * Author(s) Ian Holder,
 */

import java.util.HashMap;

public class SaveDataTranslator implements SaveDataTranslatorInterface{
    //called on application close to wrap user data into a JSON to be passed to file storage
    public static void saveUserData(){
        //create a JSON of the user data
        HashMap<String,Integer> userGenreMap = User.getUserGenres().toMap();
        HashMap<String,Integer> userTagsMap = User.getUserTags().toMap();
        HashMap<Integer,String> userViewedGames = User.getViewedGames();

    }

    //called on application start to take in a json from file and set the user data
    public static Boolean loadUserData(){
        return false;
    }
}
