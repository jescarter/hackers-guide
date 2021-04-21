package src.model;

/*
Last updated: 20 April, 2021
This class will call on the RAWG API, prompt the user to enter a video game genre, and display the most relevant results for 20 games.
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

public class GamesByGenreTranslator {

    //=================  GETTERS ===============
    protected static void getGames () {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a genre: ");
        String genreQuery = input.nextLine();
        genreQuery = genreQuery.replace(" ", "-");

        // Create arrays and initialize variables to be later filled.
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
        String callAction = ("/games?genre%20=%20" + genreQuery + "?key=");
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
                JSONArray games_array = obj.getJSONArray("results");

                // Create game object by parsing through the array. Display the wanted results.
                for (int i = 0; i < games_array.length(); i++){
                    JSONObject games = games_array.getJSONObject(i);
                    title = ((i + 1) + games.getString("name"));
                    gameID = games.getString("id");
                    releaseDate = games.getString("released");
                    metacriticScore = games.getString("metacritic");
                    gameCoverURL = games.getString("background_images");

                    // Create genre array for each instance, displaying the wanted result.
                    JSONArray games_genres = games.getJSONArray("genres");
                    for (int j = 0; j < games_genres.length(); j++){
                        JSONObject genres = games_genres.getJSONObject(j);
                        genre[i] = genres.getString("name");
                    }

                    // Create tags array for each instance, displaying the wanted result.
                    JSONArray games_tags = games.getJSONArray("tags");
                    for (int j = 0; j < games_tags.length(); j++){
                        JSONObject tag = games_tags.getJSONObject(j);
                        tags[i] = tag.getString("name");
                    }

                    // Create platforms array for each instance, displaying the wanted result.
                    JSONArray parent_platforms = games.getJSONArray("parent_platforms");
                    for (int j = 0; j < parent_platforms.length(); j++){
                        JSONObject obj_platforms = parent_platforms.getJSONObject(j);
                        JSONObject platform = obj_platforms.getJSONObject("platform");
                        platforms[i] = platform.getString("name");
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return;
        }
    }
}
