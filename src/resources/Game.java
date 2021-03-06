package resources;

/*
 * objects with api data to be used for views
 * last updated 04/27/2021
 * Author(s) Ian Holder,
 */

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Game {
    private final String[] genre;
    private final String releaseDate;
    private final String title;
    private final String[] tags;
    private final String[] platforms;
    private final String metacriticScore;
    private URL gameCoverURL;
    private Path coverFilePath;
    private final String gameID;

    //constructor
    public Game(String[] _genre, String _title, String[] _tags, String _metacriticScore, String _gameCoverURL,
                String _releaseDate, String[] _platforms, String _gameID) {
        this.genre = _genre;
        this.title = _title;
        this.tags = _tags;
        this.metacriticScore = _metacriticScore;
        this.releaseDate = _releaseDate;
        this.platforms = _platforms;
        this.gameID = _gameID;

        //sets the file path and downloads the cover image on construction
        try {
            //creates the file with the suffix jpg so the image views can use it
            this.coverFilePath = Files.createTempFile("tmp", ".jpg");
            this.gameCoverURL = new URL(_gameCoverURL);
            InputStream inputStream = this.gameCoverURL.openStream();
            Files.copy(inputStream, this.coverFilePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //=================  GETTERS ===============
    public String getTitle() {
        return this.title;
    }

    //method to return a single string of the game genre array
    public String getGameGenreString(){
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < this.genre.length; i++) {
            outputString.append(this.genre[i]);
            if(i != (this.genre.length - 1)){
                outputString.append(", ");
            }
        }
        return outputString.toString();
    }

    public String getGamePlatformString(){
        StringBuilder outputString = new StringBuilder();
        for (int i = 0; i < this.platforms.length; i++){
            outputString.append(this.platforms[i]);
            if(i != (this.platforms.length -1)){
                outputString.append(", ");
            }
        }
        return outputString.toString();
    }

    public String[] getGenre() {
        return this.genre;
    }

    public String getGameID(){
        return this.gameID;
    }

    public String getMetacriticScore() {
        return this.metacriticScore;
    }

    public String[] getTags() {
        return this.tags;
    }

    public String[] getPlatforms() {
        return platforms;
    }

    public String getReleaseDate(){
        return this.releaseDate;
    }

    public Path getCoverFilePath() {
        return this.coverFilePath;
    }

    public URL getGameCoverURL() {
        return this.gameCoverURL;
    }

    public Boolean hasGenre(String _genre){
        //check that the array contains anything
        if(this.genre.length == 0){
            return false;
        }
        //transverse the array to find the search term
        for (String placeHolder:this.genre) {
            if(placeHolder.equals(_genre)){
                return true;
            }
        }
        //if the array does not have the string return false
        return false;
    }

    public Boolean hasTag(String _tag){
        if(this.tags.length == 0 || this.genre == null){
            return false;
        }
        for (String placeHolder:this.tags) {
            if(placeHolder.equals(_tag)){
                return true;
            }
        }
        return false;
    }

    //=================  SETTERS ===============
    //to set image path after construction
    public void setCoverFilePath(){
        Path newPath;
        try {
            newPath = Files.createTempFile(title, ".jpg");
            InputStream inputStream = this.gameCoverURL.openStream();
            Files.copy(inputStream, newPath, StandardCopyOption.REPLACE_EXISTING);
            this.coverFilePath = newPath;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
