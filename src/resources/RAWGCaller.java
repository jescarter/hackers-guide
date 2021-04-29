package resources;

/*
Last updated: 28  April, 2021
This class will call on the RAWG API
Authors: Emily Crabtree, Ian Holder
*/

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RAWGCaller implements APICallerInft {
    private static final String baseURL = "https://api.rawg.io/api";
    private static final String apiKey = "6346c4bd4d004ac58323138cd49d65cb";

    @Override
    public JSONObject getGamesByGenre(String _genre, int _page) {
        //build the url
        String genreQuery = _genre.replace(" ", "-");
        String callAction = ("/games?genres=" + genreQuery.toLowerCase() + "&key=");
        String urlString = baseURL + callAction + apiKey + "&page=" + _page;
        return call(urlString);
    }

    @Override
    public JSONObject getGamesByTag(String _tag, int _page) {
        //build the url
        String tagQuery = _tag.replace(" ", "-");
        String callAction = ("/games?tags=" + tagQuery.toLowerCase() + "&key=");
        String urlString = baseURL + callAction + apiKey + "&page=" + _page;
        //make the json to be returned outside the try catch for scoping
        return call(urlString);
    }

    @Override
    public JSONObject getGenres() {
        // Create a HTTP Connection.
        String baseUrl = "https://api.rawg.io/api/genres?key=";
        String apiKey = "6346c4bd4d004ac58323138cd49d65cb";
        String urlString = baseUrl + apiKey;
        return call(urlString);
    }

    @Override
    public JSONObject getTags() {
        // Create a HTTP Connection.
        String baseUrl1 = "https://api.rawg.io/api/tags?page_size=60&key=";
        String urlString1 = baseUrl1 + apiKey;

        String baseUrl2 = "https://api.rawg.io/api/tags?page=2&page_size=60&key=";
        String urlString2 = baseUrl2 + apiKey;

        //get the first page results
        JSONObject json1 = call(urlString1);
        //get page two results
        JSONObject json2 = call(urlString2);
        JSONObject toReturn = new JSONObject();
        try {
            JSONArray tagsArray1 = json1.getJSONArray("results");
            JSONArray tagsArray2 = json2.getJSONArray("results");
            toReturn.put("Array1",tagsArray1);
            toReturn.put("Array2",tagsArray2);
            return toReturn;
        }catch(Exception e){
            e.printStackTrace();
        }
        return toReturn;
    }

    private JSONObject call(String _callURL){
        JSONObject toBeReturned = new JSONObject();
        URL url;
        try {
            // Make the connection.
            url = new URL(_callURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Examine the response code.
            int status = con.getResponseCode();
            if (status != 200) {
                System.out.printf("Error: Could not load game: " + status);
            } else {
                // Parsing input stream into a text string.
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                // Close the connections.
                in.close();
                con.disconnect();
                // Parse that object into a usable Java JSON object.
                toBeReturned = new JSONObject(content.toString());
                return toBeReturned;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return toBeReturned;
    }
}
