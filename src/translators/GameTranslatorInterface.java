package translators;

/*
 * an interface for the game translator
 * last updated 04/22/2021
 * Author(s) Ian Holder,
 */

import resources.Game;

import java.util.HashMap;

public interface GameTranslatorInterface {
    public static Game[] getGamesByGenre(String _genre){
        return null;
    }
    public static Game[] getGamesByTag(String _tag){
        return null;
    }
    public static HashMap<String, Integer> getGenres(){
        return null;
    }
    public static HashMap<String,Integer> getTags(){
        return null;
    }
}
