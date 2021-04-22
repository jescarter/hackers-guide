package game;

/*
 * a model to create/return games from the api
 * last updated 04/22/2021
 * Author(s) Ian Holder,
 */

import user.User;
import gameGenie.GameController;
import resources.Game;
import resources.GameQueue;
import translators.GamesByGenreTranslator;



public class GameFactory {
    private static String[] gameGenres;
    private static String[] gameTags;

    public static GameQueue<Game> getGameQueue(){
        if(gameGenres == null || gameTags == null){
            setGameGenres(getGenres());
            setGameTags(getTags());
        }
        //create the game queue
        GameQueue<Game> toBeReturned = new GameQueue<>();
        //make the game array to call the API for game objects
        Game[] placeHolder = GamesByGenreTranslator.getGames(gameGenres[(int) (Math.random() * gameGenres.length) - 1]);
        //check each game in the array
        for (Game placeHoldingGame:placeHolder) {
            //check that the games in the array have not been rated
            if(!GameController.wasGameViewed(placeHoldingGame.getGameID())){
                //put game in the queue
                toBeReturned.offer(placeHoldingGame);
            }
        }
        return toBeReturned;
    }

    public static Game getRecommendation(){
        Game recommendation = null;
        Game[] placeHolder = GamesByGenreTranslator.getGames(User.getMostLikedGenre());
        for (Game game : placeHolder) {
            //insure that the game has not been rated
            if(!GameController.wasGameViewed(game.getGameID())) {
                //on the first loop the recommendation game object will be set to the first element in the array
                if (recommendation == null) {
                    recommendation = game;
                }
                //in the case that the recommendation game has the same tag as another game in the array, take the highest score
                if (game.hasTag(User.getMostLikedTag()) && recommendation.hasTag(User.getMostLikedTag())) {
                    if (!game.getMetacriticScore().equals(recommendation.getMetacriticScore())) {
                        recommendation = game;
                    }
                }
                //if recommendation does not have the most liked tag and game does
                if(game.hasTag(User.getMostLikedTag()) && !recommendation.hasTag(User.getMostLikedTag())){
                    recommendation = game;
                }
            }
        }
        return recommendation;
    }

    //for input guarding get an array of all valid genres
    public static String[] getGenres(){
        return null;
    }

    //input guarding get all the valid tags
    public static String[] getTags(){
        return null;
    }

    //================= SETTERS ===============
    private static void setGameGenres(String[] _inputArray){
        gameGenres = _inputArray;
    }

    private static void setGameTags(String[] _inputArray){
        gameTags = _inputArray;
    }
}
