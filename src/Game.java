package src;

/**
 * Create game objects with api data to be used for views
 * last updated 03/30/2021
 * Author(s) Ian Holder,
 */

public class Game {
    String genre;
    int genreID;
    String title;
    String[] tags;
    int metacriticScore;
    int playtime;

    public void game(String _genre, int _genreID, String _title, String[] _tags, int _metacriticScore, int _playtime){
        genre = _genre;
        genreID = _genreID;
        title = _title;
        tags = _tags;
        metacriticScore = _metacriticScore;
        playtime = _playtime;
    }

    //=================  GETTERS ===============

    public String getTitle(){
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

}
