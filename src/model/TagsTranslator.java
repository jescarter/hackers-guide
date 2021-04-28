package src.model;

/*
Last updated: 20  April, 2021
This class will call on the RAWG API and display all possible tags and their ID.
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

    // Create three String arrays, one to collect results from page 1, one to collect results from page 2, one to join
    // the two arrays. Create int count to merge two string arrays.
    static String[] tags = new String[80];
    static String[] tags1 = new String[40];
    static String[] tags2 = new String[40];
    static int count = 0;

    //=================  GETTERS ===============
    protected static String[] getTags() {

        String apiKey = "6346c4bd4d004ac58323138cd49d65cb";

        // Create a HTTP Connection.
        String baseUrl1 = "https://api.rawg.io/api/tags?page_size=60&key=";
        String urlString1 = baseUrl1 + apiKey;

        String baseUrl2 = "https://api.rawg.io/api/tags?page=2&page_size=60&key=";
        String urlString2 = baseUrl2 + apiKey;

        URL url;
        URL url2;

        try {
            // Make the connection.
            url = new URL(urlString1);
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

                // Print out the results by parsing through the tags_array, turning each result into an object, and displaying.
                System.out.println("Tags: ");
                for (int i = 0; i < tags_array.length(); i++) {
                    JSONObject obj_tags = tags_array.getJSONObject(i);
                    tags1[i] = obj_tags.getString("name");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }

        try {
            // Make the connection.
            url2 = new URL(urlString2);
            HttpURLConnection con = (HttpURLConnection) url2.openConnection();
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

                // Print out the results by parsing through the tags_array, turning each result into an object, and displaying.
                for (int i = 0; i < tags_array.length(); i++) {
                    JSONObject obj_tags = tags_array.getJSONObject(i);
                    tags2[i] = obj_tags.getString("name");
                }

                for (int i = 0; i < tags1.length; i++){
                    tags[i] = tags1[i];
                    count++;
                }
                for (int j = 0; j < tags2.length; j++){
                    tags[count++] = tags2[j];
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
        return tags;
    }
}
