package model;

/*
Last updated: 8 April, 2021
This class will call on the RAWG API, prompt the user to enter a video game tag, and display the most relevant results for 20 games.
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


public class GamesByTagTranslator {

    //=================  GETTERS ===============
    public static void getGames () {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a tag: ");
        String tag = input.nextLine();
        tag = tag.replace(" ", "-");

        // Create a HTTP Connection.
        String baseUrl = "https://api.rawg.io/api";
        String callAction = ("/games?tag%20=%20" + tag + "?key=");
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

                for (int i = 0; i < games_array.length(); i++){
                    JSONObject games = games_array.getJSONObject(i);
                    System.out.println("Game " + (i + 1) + ": " + games.getString("name"));
                    System.out.println("Game ID: " + games.getString("id"));
                    System.out.println("Release date: " + games.getString("released"));
                    System.out.println("Rating: " + games.getString("metacritic"));
                    System.out.println("Image: " + games.getString("background_image"));

                    JSONArray games_genres = games.getJSONArray("genres");
                    System.out.println("Genres: ");
                    for (int j = 0; j < games_genres.length(); j++){
                        JSONObject genres = games_genres.getJSONObject(j);
                        System.out.println(genres.getString("name"));
                    }

                    JSONArray games_tags = games.getJSONArray("tags");
                    System.out.println("Tags: ");
                    for (int j = 0; j < games_tags.length(); j++){
                        JSONObject tags = games_tags.getJSONObject(j);
                        System.out.println(tags.getString("name"));
                    }

                    JSONArray parent_platforms = games.getJSONArray("parent_platforms");
                    System.out.println("Platforms: ");
                    for (int j = 0; j < parent_platforms.length(); j++){
                        JSONObject obj_platforms = parent_platforms.getJSONObject(j);
                        JSONObject platforms = obj_platforms.getJSONObject("platform");
                        System.out.println(platforms.getString("name"));
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return;
        }
    }
}