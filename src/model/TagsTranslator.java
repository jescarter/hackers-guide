package Model;
/*
Last updated: 6 April, 2021
This class will call on the RAWG API and display all possible tags.
Authors: Emily Crabtree
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class TagsTranslator {

    //=================  GETTERS ===============
    public static void getTags () {

        // https://api.rawg.io/api/platforms?key=bebda822617e46b9bd3c5af8402b1a24
        // Create a HTTP Connection.
        String baseUrl = "https://api.rawg.io/api/tags?key=";
        String apiKey = "bebda822617e46b9bd3c5af8402b1a24";
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
                System.out.printf("Error: Could not load tags: " + status);
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
                JSONArray tags_array = obj.getJSONArray("results");

                // Print out the results.
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
