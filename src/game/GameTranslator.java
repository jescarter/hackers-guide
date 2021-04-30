package game;

/*
  takes in the jsons from the api caller and makes game objects from them
  last updated 04/28/2021
  Author(s) Ian Holder, Emily Crabtree
 */

import API.APICallerIntf;
import API.RAWGCaller;
import org.json.JSONArray;
import org.json.JSONObject;
import resources.Game;

public class GameTranslator implements GameTranslatorIntf {
    private APICallerIntf myCaller;

    @Override
    public Game[] getGamesByGenre(String _genre, int _page) {
        if(this.myCaller == null) {
            setMyCaller(new RAWGCaller());
        }
        JSONObject toParse = myCaller.getGamesByGenre(_genre, _page);
        return parseGameJSON(toParse);
    }

    @Override
    public Game[] getGamesByTag(String _tag, int _page) {
        if(this.myCaller == null) {
            setMyCaller(new RAWGCaller());
        }
        JSONObject toParse = myCaller.getGamesByTag(_tag, _page);
        return parseGameJSON(toParse);
    }

    @Override
    public String[] getGenres() {
        setMyCaller(new RAWGCaller());
        JSONObject toParse = myCaller.getGenres();
        // Create genre array of 19 to be later filled.
        String[] genre = new String[19];
        try{
            JSONArray genres_array = toParse.getJSONArray("results");

            // Print out the results by parsing through genres_array, turning each result into an object, and displaying.
            for (int i = 0; i < genres_array.length(); i++){
                JSONObject obj_genres = genres_array.getJSONObject(i);
                genre[i] = obj_genres.getString("name");
            }
            return genre;
        }catch (Exception e){
            e.printStackTrace();
        }
        return genre;
    }

    @Override
    public String[] getTags() {
        if(this.myCaller == null) {
            setMyCaller(new RAWGCaller());
        }
        JSONObject toParse = myCaller.getTags();
        String[] toReturn = new String[80];
        try{
            JSONArray array1 = toParse.getJSONArray("Array1");
            JSONArray array2 = toParse.getJSONArray("Array2");
            for(int i = 0; i < array1.length(); i++){
                JSONObject tempTag = array1.getJSONObject(i);
                toReturn[i] = tempTag.getString("name");
            }
            for(int i = 0; i < array2.length(); i++){
                JSONObject tempTag = array2.getJSONObject(i);
                toReturn[i + array1.length()] = tempTag.getString("name");
            }
            return toReturn;
        }catch (Exception e){
            e.printStackTrace();
        }
        return toReturn;
    }

    private Game[] parseGameJSON(JSONObject _toParse) {
        // Create arrays and initialize variables to be later filled.
        String[] genre;
        String releaseDate;
        String title;
        String[] tags;
        String[] platforms;
        String metacriticScore;
        String gameCoverURL;
        String gameID;
        Game game;
        Game[] toReturn = new Game[20];
        try {
            JSONArray gamesArray = _toParse.getJSONArray("results");
            // Create game object by parsing through the array. Display the wanted results.
            for (int i = 0; i < gamesArray.length(); i++){
                JSONObject games = gamesArray.getJSONObject(i);
                genre = new String[games.getJSONArray("genres").length()];
                tags = new String[games.getJSONArray("tags").length()];
                platforms = new String[games.getJSONArray("parent_platforms").length()];
                title = games.getString("name");
                gameID = games.getString("id");
                releaseDate = games.getString("released");
                metacriticScore = games.getString("metacritic");
                gameCoverURL = games.getString("background_image");

                // Create genre array for each instance, displaying the wanted result.
                JSONArray gamesGenres = games.getJSONArray("genres");
                for (int j = 0; j < gamesGenres.length(); j++){
                    JSONObject genres = gamesGenres.getJSONObject(j);
                    genre[j] = genres.getString("name");
                }

                // Create tags array for each instance, displaying the wanted result.
                JSONArray gamesTags = games.getJSONArray("tags");
                for (int j = 0; j < gamesTags.length(); j++){
                    JSONObject tag = gamesTags.getJSONObject(j);
                    tags[j] = tag.getString("name");
                }

                // Create platforms array for each instance, displaying the wanted result.
                JSONArray parentPlatforms = games.getJSONArray("parent_platforms");
                for (int j = 0; j < parentPlatforms.length(); j++){
                    JSONObject obj_platforms = parentPlatforms.getJSONObject(j);
                    JSONObject platform = obj_platforms.getJSONObject("platform");
                    platforms[j] = platform.getString("name");
                }

                // Create game object and then place all games into a Game object array.
                game = new Game(genre, title, tags, metacriticScore, gameCoverURL, releaseDate, platforms, gameID);
                toReturn[i] = game;
            }
            return toReturn;
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
            return null;
        }
    }

    //inject the caller into the translator
    public void setMyCaller(APICallerIntf _inputCaller){
        this.myCaller = _inputCaller;
    }
}
