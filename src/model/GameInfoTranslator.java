package src.model;

/*
Last updated: 8 April, 2021
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
    public static void getVideoGameInfo () {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a video game search query: ");
        String query = input.nextLine();
        query = query.replace(" ", "-");


        // https://api.rawg.io/api/platforms?key=bebda822617e46b9bd3c5af8402b1a24
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
                String name = obj.getString("name_original");
                String date = obj.getString("released");
                String description = obj.getString("description");
                String rating = obj.getString("metacritic");
                String imageURL = obj.getString("background_image");
                String websiteURL = obj.getString("website");

                // Create arrays of wanted results.
                JSONArray parent_platforms = obj.getJSONArray("parent_platforms");
                JSONArray tags_array = obj.getJSONArray("tags");
                JSONArray genres_array = obj.getJSONArray("genres");

                // Display results
                System.out.println("Game: " + name);
                System.out.println("Release date: " + date);
                System.out.println("Description: " + description);
                System.out.println("Rating: " + rating);
                System.out.println("Image URL: " + imageURL);
                System.out.println("Website URL: " +websiteURL);

                System.out.println("Platforms: ");
                for (int i = 0; i < parent_platforms.length(); i++){
                    JSONObject obj_platforms = parent_platforms.getJSONObject(i);
                    JSONObject platforms = obj_platforms.getJSONObject("platform");
                    System.out.println(platforms.getString("name"));
                }

                System.out.println("Genres: ");
                for (int i = 0; i < genres_array.length(); i++){
                    JSONObject obj_genres = genres_array.getJSONObject(i);
                    System.out.println(obj_genres.getString("name") + ", ID: " + obj_genres.getString("id"));
                }

                System.out.println("Tags: ");
                for (int i = 0; i < tags_array.length(); i++){
                    JSONObject obj_tags = tags_array.getJSONObject(i);
                    System.out.println(obj_tags.getString("name") + ", ID: " + obj_tags.getString("id"));
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return;
        }
    }
}
