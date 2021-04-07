package src.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A class that will translate user data into a JSON format to be saved.
 * Last Updated: 04/07/2021
 * Author(s): Jesse Carter
 */

public class JSONTranslator {

    public static JSONObject translateViewedGames(HashMap<Integer, String> _viewedGames) {
        //Create a JSON array to store viewedGames
        JSONArray listData = new JSONArray();
        //Create an iterator for viewedGames list
        Iterator viewedGamesIterator = _viewedGames.entrySet().iterator();
        //While there are entries in viewedGames, create JSON objects and add them to JSONArray
        while (viewedGamesIterator.hasNext()) {
            Map.Entry currentEntry = (Map.Entry)viewedGamesIterator.next();
            JSONObject tempData = new JSONObject();
            try {
                tempData.put("id", currentEntry.getKey());
                tempData.put("title", currentEntry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            listData.put(tempData);

        }
        //Place JSONArray with all viewedGames data into a JSONObject
        JSONObject viewedGamesJSON = new JSONObject();
        try {
            viewedGamesJSON.put("viewedGames", listData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return viewedGamesJSON;
    }

    //TODO
    public static JSONObject translateUserGenres(DoubledLinkList _userGenres) {
        return new JSONObject();
    }

    //TODO
    public static JSONObject translateUserTags(DoubledLinkList _userTags) {
        return new JSONObject();
    }

    //Method to return JSON of viewedGames as a String.
    public static String translateViewedGamesToString(HashMap<Integer, String> _viewedGames) {
        return translateViewedGames(_viewedGames).toString();
    }

    //Method to return JSON of userGenres as a String.
    public static String translateUserGenresToString(DoubledLinkList _userGenres) {
        return translateUserGenres(_userGenres).toString();
    }

    //Method to return JSON of userTags as a String.
    public static String translateUserTagsToString(DoubledLinkList _userTags) {
        return translateUserGenres(_userTags).toString();
    }

}
