package gameGenie;

/*
  controller to handle creation of game objects and game queues for the game genie controller
  last updated 04/22/2021
  Author(s) Ian Holder,
 */

import game.GameFactory;
import resources.Game;
import resources.GameQueue;

public class GameController {
    protected static Game getRecommendation(){
        return new Game(new String[]{"Action"},"Hello", new String[]{"fun"}, "85", "","1950",
                new String[]{"PC"}, "1984651");
        //return GameFactory.getRecommendation();
    }

    //creat a game queue for the game picker, getting games from the API based on a random genre
    protected static GameQueue<Game> getGameQueue(){
        GameQueue<Game> placeHolder = new GameQueue<>();
        placeHolder.offer(new Game(new String[]{"Action"},"Hello", new String[]{"fun"}, "85", "","1950",
                new String[]{"PC"}, "1984651"));
        return placeHolder;
        //return GameFactory.getGameQueue();
    }

    //get if a game had been rated from the user controller for the game factory
    public static boolean wasGameViewed(String _gameID){
        return UserController.wasGameViewed(_gameID);
    }
}
