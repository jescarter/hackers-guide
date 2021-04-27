package resources;

/*
  an interface for how the game translator should work
  last updated 04/27/2021
  Author(s) Ian Holder,
 */

import game.GameTranslatorInterface;

public class GameTranslator implements GameTranslatorInterface {
    @Override
    public Game[] getGamesByGenre(String _genre) {
        return new Game[0];
    }

    @Override
    public Game[] getGamesByTag(String _tag) {
        return new Game[0];
    }

    @Override
    public String[] getGenres() {
        return new String[0];
    }

    @Override
    public String[] getTags() {
        return new String[0];
    }
}
