package gameGenie;

/*
  controller to handle creation of game objects and game queues for the game genie controller
  last updated 04/22/2021
  Author(s) Ian Holder,
 */

import game.GameFactory;
import resources.Game;
import resources.GameQueue;
import game.GameTranslator;
import user.User;

public class GameController {
    protected static Game getRecommendation(){
        GameFactory.getInstance().setGameTranslator(new GameTranslator());
        return GameFactory.getInstance().getRecommendation();
    }

    //creat a game queue for the game picker, getting games from the API based on a random genre
    protected static GameQueue<Game> getGameQueue(){
        GameFactory.getInstance().setGameTranslator(new GameTranslator());
        GameQueue<Game> myQueue = GameFactory.getInstance().getGameQueue();
        if(myQueue.isEmpty()){
            myQueue = GameFactory.getInstance().getGameQueue();
        }
        return myQueue;
    }

    //get if a game had been rated from the user controller for the game factory
    public static boolean wasGameViewed(String _gameID){
        return User.getInstance().wasViewed(_gameID);
    }

    public static String mostLikedGenre(){
        return User.getInstance().getMostLikedGenre();
    }

    public static String mostLikedTag(){
        return User.getInstance().getMostLikedTag();
    }
}
