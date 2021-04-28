package game;

/*
 * an interface for the game translator
 * last updated 04/27/2021
 * Author(s) Ian Holder,
 */

import resources.Game;

public interface GameTranslatorInterface {
    public Game[] getGamesByGenre(String _genre);

    public Game[] getGamesByTag(String _tag);

    public String[] getGenres();

    public String[] getTags();

}
