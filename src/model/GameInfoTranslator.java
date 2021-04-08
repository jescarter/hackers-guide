package model;
/*
Last updated: 24 March, 2021
This class will call on the RAWG API, prompt the user to enter a video game search query, and display the most relevant results.
Authors: Emily Crabtree
*/

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class GameInfoTranslator {

    //=================  GETTERS ===============
   public static void getVideoGameInfo () {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a video game search query: ");
        String query = input.nextLine();
        query = query.replace(" ", "-");
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://rawg-video-games-database.p.rapidapi.com/games/" + query))
                    .header("x-rapidapi-key", "d5dbc65664mshead682c1774a2bfp18189bjsnfc136a7d5b0d")
                    .header("x-rapidapi-host", "rawg-video-games-database.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            // Parse object into a usable Java JSON object.
            JSONObject obj = new JSONObject(response.body());

            // Print out the results.
            String name = obj.getString("name_original");
            String date = obj.getString("released");
            String description = obj.getString("description");
            description = description.replace("<p>", "");
            description = description.replace("&#39;", "'");
            description = description.replace("<br />", "");
            description = description.replace("</p>", "");
            String rating = obj.getString("rating");

            JSONArray parent_platforms = obj.getJSONArray("parent_platforms");

            System.out.println("Game: " + name);
            System.out.println("Release date: " + date);
            System.out.println("Description: " + description);
            System.out.println("Rating: " + rating);

            System.out.print("Platforms: ");
            for (int i = 0; i < parent_platforms.length(); i++){
                JSONObject obj_platforms = parent_platforms.getJSONObject(i);
                JSONObject platforms = obj_platforms.getJSONObject("platform");
                System.out.println(platforms.getString("name"));
            }

        } catch (Exception ex) {
            System.out.println("Error. Video game not found.");
        }
    }
}
