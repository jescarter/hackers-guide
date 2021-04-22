package gameGenie;

/*
  controller to handle creation of game objects and game queues for the game genie controller
  last updated 04/20/2021
  Author(s) Ian Holder,
 */

import resources.Game;
import translators.GamesByGenreTranslator;
import User.User;
import resources.GameQueue;

import static resources.Util.genresArray;

public class GameController {
    protected static Game getRecommendation(){
        Game recommendation = null;
        Game[] placeHolder = GamesByGenreTranslator.getGames(User.getMostLikedGenre());
        for (Game game : placeHolder) {
            //on the first loop the recommendation game object will be set to the first element in the array
            if (recommendation == null) {
                recommendation = game;
            }
            //in the case that the recommendation game has the same tag as another game in the array, take the highest score
            if (game.hasTag(User.getMostLikedTag()) && recommendation.hasTag(User.getMostLikedTag())) {
                if (game.getMetacriticScore() > recommendation.getMetacriticScore()) {
                    recommendation = game;
                }
            }
            //if recommendation does not have the most liked tag and game does
            if(game.hasTag(User.getMostLikedTag()) && !recommendation.hasTag(User.getMostLikedTag())){
                recommendation = game;
            }
        }
        return recommendation;
    }

    //creat a game queue for the game picker, getting games from the API based on a random genre
    protected static GameQueue<Game> getGameQueue(){
        //create the game queue
        GameQueue<Game> toBeReturned = new GameQueue<>();
        //make the game array to call the API for game objects
        Game[] placeHolder = GamesByGenreTranslator.getGames(genresArray[(int) (Math.random() * genresArray.length) - 1]);
        //check each game in the array
        for (Game placeHoldingGame:placeHolder) {
            //check that the games in the array have not been rated
            if(!User.wasViewed(placeHoldingGame.getGameID())){
                //put game in the queue
                toBeReturned.offer(placeHoldingGame);
            }
        }
        return toBeReturned;
    }
}
