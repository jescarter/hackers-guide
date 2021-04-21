package src.model;

/*
Last updated: 20 April, 2021
This class will call on the RAWG API, prompt the user to enter a video game search query, and display the most relevant results.
Authors: Emily Crabtree
*/

import java.util.Scanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class GameInfoTranslator {

    //=================  GETTERS ===============
    protected static void getVideoGameInfo () {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a video game search query: ");
        String query = input.nextLine();
        query = query.replace(" ", "-");

        // Create needed arrays and initialize variables to be later filled.
        String[] genre = new String[20];
        String[] tags = new String[20];
        String[] platforms = new String[20];
        String title;
        String releaseDate;
        String description;
        String metacriticScore;
        String gameCoverURL;
        String gameID;


        // Create a HTTP Connection.
        String baseUrl = "https://api.rawg.io/api";
        String callAction = ("/games/" + query + "?key=");
        String apiKey = "bebda822617e46b9bd3c5af8402b1a24";
        String urlString = baseUrl + callAction + apiKey;

        URL url;
        try {
            // Make the connection.
            url = new URL(urlString);
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
                JSONObject obj = new JSONObject(content.toString());

                // Create object strings of results.
                title = obj.getString("name_original");
                releaseDate = obj.getString("released");
                description = obj.getString("description");
                metacriticScore = obj.getString("metacritic");
                gameCoverURL = obj.getString("background_image");
                gameID = obj.getString("id");

                // Create arrays of wanted results.
                JSONArray parent_platforms = obj.getJSONArray("parent_platforms");
                JSONArray tags_array = obj.getJSONArray("tags");
                JSONArray genres_array = obj.getJSONArray("genres");

                // Parse through parent_platforms array, turning into an object and displaying each result
                for (int i = 0; i < parent_platforms.length(); i++){
                    JSONObject obj_platforms = parent_platforms.getJSONObject(i);
                    JSONObject platform = obj_platforms.getJSONObject("platform");
                    platforms[i] = platform.getString("name");
                }

                // Parse through genres_array, turning into an object and displaying each result.
                for (int i = 0; i < genres_array.length(); i++){
                    JSONObject obj_genres = genres_array.getJSONObject(i);
                    genre[i] = obj_genres.getString("name");
                }

                // Parse through tags_array, turning into an object and displaying each result
                for (int i = 0; i < tags_array.length(); i++){
                    JSONObject obj_tags = tags_array.getJSONObject(i);
                    tags[i] = obj_tags.getString("name");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return;
        }
    }
}
