package user;

/*
 * interface for how the save translator should work
 * last updated 04/21/2021
 * Author(s) Ian Holder,
 */

import org.json.JSONObject;

public interface SaveDataTranslatorInterface {
    //will get the user data from the user class and call a helper to wrap into a json to pass to be written to a file
    public static void saveUserData(){

    }

    //will call to get a json from a file to be opened to set the data in the user class on application open
    public static boolean loadUserData(){
        return false;
    }

}
