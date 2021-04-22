package gameGenie;

/*
  controller to handle creation of game objects and game queues for the game genie controller
  last updated 04/22/2021
  Author(s) Ian Holder,
 */

import game.GameFactory;
import resources.Game;
import translators.GamesByGenreTranslator;
import User.User;
import resources.GameQueue;

import static resources.Util.genresArray;

public class GameController {
    protected static Game getRecommendation(){
        return GameFactory.getRecommendation();
    }

    //creat a game queue for the game picker, getting games from the API based on a random genre
    protected static GameQueue<Game> getGameQueue(){
        return GameFactory.getGameQueue();
    }
}
