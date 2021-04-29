package game;

/*
 * an interface for the game translator
 * last updated 04/27/2021
 * Author(s) Ian Holder,
 */

import resources.Game;

public interface GameTranslatorIntf {
    public Game[] getGamesByGenre(String _genre, int _page);

    public Game[] getGamesByTag(String _tag, int _page);

    public String[] getGenres();

    public String[] getTags();

}
