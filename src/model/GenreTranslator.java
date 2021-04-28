package src.model;

/*
Last updated: 28 April, 2021
This class will call on the RAWG API and display all possible genres.
Authors: Emily Crabtree
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class GenreTranslator {

    // Create genre array of 19 to be later filled.
    String[] genre = new String[19];

    //=================  GETTERS ===============
    protected String[] getGenres () {

        // Create a HTTP Connection.
        String baseUrl = "https://api.rawg.io/api/genres?key=";
        String apiKey = "6346c4bd4d004ac58323138cd49d65cb";
        String urlString = baseUrl + apiKey;

        URL url;
        try {
            // Make the connection.
            url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            // Examine the response code.
            int status = con.getResponseCode();
            if (status != 200) {
                System.out.printf("Error: Could not load genres: " + status);
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
                
                // Parse that object into a usable Java JSON object and into a JSON array.
                JSONObject obj = new JSONObject(content.toString());
                JSONArray genres_array = obj.getJSONArray("results");

                // Print out the results by parsing through genres_array, turning each result into an object, and displaying.
                for (int i = 0; i < genres_array.length(); i++){
                    JSONObject obj_genres = genres_array.getJSONObject(i);
                    genre[i] = obj_genres.getString("name");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
        return genre;
    }
}
