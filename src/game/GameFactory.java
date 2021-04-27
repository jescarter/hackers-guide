package game;

/*
 * a model to return games sent from the game translator, after being checked
 * last updated 04/27/2021
 * Author(s) Ian Holder,
 */

import user.User;
import gameGenie.GameController;
import resources.Game;
import resources.GameQueue;

public class GameFactory {
    private static String[] gameGenres;
    private static String[] gameTags;
    private static GameTranslatorInterface gameTranslator;

    public static GameQueue<Game> getGameQueue(){
        if(gameGenres == null || gameTags == null){
            setGameGenres(gameTranslator.getGenres());
            setGameTags(gameTranslator.getTags());
        }
        //create the game queue
        GameQueue<Game> toBeReturned = new GameQueue<>();
        //make the game array to call the API for game objects
        Game[] placeHolder = gameTranslator.getGamesByGenre(gameGenres[(int) (Math.random() * gameGenres.length) - 1]);
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
        //call for the api to get games of the user's most liked genre
        String favoriteGenre = User.getInstance().getMostLikedGenre();
        String favoriteTag = User.getInstance().getMostLikedTag();
        //if the users favorite genre is null then the tags are also null
        if(favoriteGenre == null){
            favoriteGenre = gameGenres[(int) (Math.random() * gameGenres.length) - 1];
            favoriteTag = gameTags[(int) (Math.random() * gameTags.length) - 1];
        }
        Game[] placeHolder = gameTranslator.getGamesByGenre(favoriteGenre);
        for (Game game : placeHolder) {
            //insure that the game has not been rated
            if(!GameController.wasGameViewed(game.getGameID())) {
                //on the first loop the recommendation game object will be set to the first element in the array
                if (recommendation == null) {
                    recommendation = game;
                }
                //in the case that the recommendation game has the same tag as another game in the array, take the highest score
                recommendation = compareGameBasedOnTag(recommendation, game, favoriteTag);
            }
        }
        return recommendation;
    }

    private static Game compareGameBasedOnTag(Game _game1, Game _game2, String _favoriteTag){
        //if both games have the favorite tag take the one that has the higher score
        if(_game1.hasTag(_favoriteTag) && _game1.hasTag(_favoriteTag)){
            if(Integer.parseInt(_game1.getMetacriticScore()) < Integer.parseInt(_game2.getMetacriticScore())){
                //game 2 has the higher score
                return _game2;
            }else{
                //game 1 has the higher score
                return _game1;
            }
            //if game 2 does not have the favorite tag return game 1
        }else if(_game1.hasTag(_favoriteTag) && !_game2.hasTag(_favoriteTag)){
            return _game1;
            //if game 1 does not have the favorite tag see if game 2 does
        }else if(_game2.hasTag(_favoriteTag)){
            return _game2;
        }else{
            return _game1;
        }
    }

    //for input guarding get an array of all valid genres
    public static String[] getGenres(){
        return gameTranslator.getGenres();
    }

    //input guarding get all the valid tags
    public static String[] getTags(){
        return gameTranslator.getTags();
    }

    //================= SETTERS ===============
    private static void setGameGenres(String[] _inputArray){
        gameGenres = _inputArray;
    }

    private static void setGameTags(String[] _inputArray){
        gameTags = _inputArray;
    }

    public static void setGameTranslator(GameTranslatorInterface _inputTranslator){
        gameTranslator = _inputTranslator;
    }
}
