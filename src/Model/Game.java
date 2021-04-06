package Model;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;

/**
 * Create game objects with api data to be used for views
 * last updated 03/30/2021
 * Author(s) Ian Holder,
 */

public class Game {
    protected String genre;
    protected int genreID;
    protected String title;
    protected String[] tags;
    protected int metacriticScore;
    protected int playtime;
    protected URL gameCoverURL;
    protected Path coverFilePath;

    public Game(String _genre, int _genreID, String _title, String[] _tags, int _metacriticScore, int _playtime, String _gameCoverURL) {
        genre = _genre;
        genreID = _genreID;
        title = _title;
        tags = _tags;
        metacriticScore = _metacriticScore;
        playtime = _playtime;
        try {
            coverFilePath = Files.createTempFile("tmp", ".jpg");
            gameCoverURL = new URL(_gameCoverURL);
            InputStream inputStream = gameCoverURL.openStream();
            Files.copy(inputStream, coverFilePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //=================  GETTERS ===============

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getGenreID() {
        return genreID;
    }

    public int getMetacriticScore() {
        return metacriticScore;
    }

    public int getPlaytime() {
        return playtime;
    }

    public String[] getTags() {
        return tags;
    }

    public Path getCoverFilePath() {
        return coverFilePath;
    }

    public URL getGameCoverURL() {
        return gameCoverURL;
    }

    //=================  SETTERS ===============
    public void setCoverFilePath(){
        Path newPath;
        try {
            newPath = Files.createTempFile(title, ".jpg");
            InputStream inputStream = gameCoverURL.openStream();
            Files.copy(inputStream, newPath, StandardCopyOption.REPLACE_EXISTING);
            coverFilePath = newPath;
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
