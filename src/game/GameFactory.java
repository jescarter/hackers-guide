package game;

/*
 * a model to return games sent from the game translator, after being checked
 * last updated 04/28/2021
 * Author(s) Ian Holder,
 */

import user.User;
import gameGenie.GameController;
import resources.Game;
import resources.GameQueue;

import java.util.HashMap;

public class GameFactory {
    private String[] gameGenres;
    private String[] gameTags;
    private GameTranslatorInterface gameTranslator;
    private static GameFactory gameFactory;

    //================= GETTERS ===============
    public GameQueue<Game> getGameQueue(int _genreChangerByForce){
        HashMap<String,String> gamesInQueue = new HashMap<>();

        if(this.gameGenres == null || this.gameTags == null){
            setGameGenres(gameTranslator.getGenres());
            setGameTags(gameTranslator.getTags());
        }
        int funTestingGenre = (((int) (Math.random() * this.gameGenres.length)) + _genreChangerByForce) % this.gameGenres.length;
        int funTestingTags = (((int) (Math.random() * this.gameTags.length))) % this.gameTags.length;
        System.out.println("Searching Genre is " + gameGenres[funTestingGenre]);
        System.out.println("Searching Tag is " + gameTags[funTestingTags]);
        //create the game queue
        GameQueue<Game> toBeReturned = new GameQueue<>();
        //make the game array to call the API for game objects
        Game[] placeHolder = gameTranslator.getGamesByGenre(this.gameGenres[funTestingGenre]);
        Game[] placeHolder2 = gameTranslator.getGamesByTag(this.gameTags[funTestingTags]);
        //check each game in the array
        for (Game placeHoldingGame:placeHolder) {
            //check that the games in the array have not been rated
            if(!GameController.wasGameViewed(placeHoldingGame.getGameID())){
                //put game in the queue
                toBeReturned.offer(placeHoldingGame);
                gamesInQueue.put(placeHoldingGame.getTitle(), placeHoldingGame.getGameID());
            }
        }
        for (Game placeHolderTag:placeHolder2) {
            if(!GameController.wasGameViewed(placeHolderTag.getGameID()) && !gamesInQueue.containsKey(placeHolderTag.getTitle())){
                toBeReturned.offer(placeHolderTag);
            }
        }
        //if on pass it can not get a game queue recur
        return toBeReturned;

    }

    public Game getRecommendation(){
        Game recommendation = null;
        //call for the api to get games of the user's most liked genre
        String favoriteGenre = User.getInstance().getMostLikedGenre();
        String favoriteTag = User.getInstance().getMostLikedTag();
        //if the users favorite genre is null then the tags are also null
        if(favoriteGenre.equals("empty")){
            favoriteGenre = gameGenres[(int) (Math.random() * gameGenres.length)];
            favoriteTag = gameTags[(int) (Math.random() * gameTags.length)];
        }
        System.out.println("Favorite Genre is " + favoriteGenre);
        System.out.println("Favorite Tag is " + favoriteTag);
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

    private Game compareGameBasedOnTag(Game _game1, Game _game2, String _favoriteTag){
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
    public String[] getGenres(){
        return gameTranslator.getGenres();
    }

    //input guarding get all the valid tags
    public String[] getTags(){
        return gameTranslator.getTags();
    }

    public static GameFactory getInstance() {
        if(gameFactory == null){
            gameFactory = new GameFactory();
        }
        return gameFactory;
    }

    //================= SETTERS ===============
    private void setGameGenres(String[] _inputArray){
        gameGenres = _inputArray;
    }

    private void setGameTags(String[] _inputArray){
        gameTags = _inputArray;
    }

    public void setGameTranslator(GameTranslatorInterface _inputTranslator){
        gameTranslator = _inputTranslator;
    }
}
