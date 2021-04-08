package model;
/*
Last updated: 24 March, 2021
This class will call on the RAWG API and display all possible genres.
Authors: Emily Crabtree
*/

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class GenreTranslator {
    public static void getGenres () {
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to see the available video game genres? (Y/N)");
        String answer = input.nextLine();
        if (answer.equals("Y")){
            try{
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://rawg-video-games-database.p.rapidapi.com/genres"))
                        .header("x-rapidapi-key", "d5dbc65664mshead682c1774a2bfp18189bjsnfc136a7d5b0d")
                        .header("x-rapidapi-host", "rawg-video-games-database.p.rapidapi.com")
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                // Parse object into a usable Java JSON object.
                JSONObject obj = new JSONObject(response.body());
                JSONArray genre_array = obj.getJSONArray("JSON");


                System.out.print("Available genres: ");
                for (int i = 0; i < genre_array.length(); i++){
                    JSONObject genres = genre_array.getJSONObject(i);
                    System.out.println(genres.getString("name"));
                }

            } catch (Exception ex) {
                System.out.println("Error.");
            }
        }
        else return;
    }
}
