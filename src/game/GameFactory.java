package game;

/*
 * a model to return games sent from the game translator, after being checked
 * last updated 04/30/2021
 * Author(s) Ian Holder,
 */

import gameGenie.GameController;
import resources.Game;
import resources.GameQueue;

import java.util.HashMap;

public class GameFactory {
    //for some reason even though the api gives RPG in this form it does not like it for the api call
    private String[] gameGenres;
    private String[] gameTags;
    private GameTranslatorIntf gameTranslator;
    private static GameFactory gameFactory;
    //these values really should be saved but meh
    private HashMap<String,Integer> pageKeeperGenres = new HashMap<>();
    private HashMap<String,Integer> pageKeeperTags = new HashMap<>();

    //helper method to see if a page of the api has all been rated
    private boolean isArrayRated(Game[] _toRate){
        for (Game tempGame:_toRate) {
            if(!GameController.wasGameViewed(tempGame.getGameID())){
                return false;
            }
        }
        return true;
    }

    //================= GETTERS ===============
    public GameQueue<Game> getGameQueue(){
        //helper to ensure no redundancies between search results
        HashMap<String,String> gamesInQueue = new HashMap<>();

        //pull the values
        if(this.gameGenres == null || this.gameTags == null){
            setGameGenres(gameTranslator.getGenres());
            setGameTags(gameTranslator.getTags());
        }
        //to help what page in the api needs to be retrieved
        if(pageKeeperGenres.isEmpty()) {
            populatePageKeepers();
        }
        //
        int funTestingGenre = (((int) (Math.random() * this.gameGenres.length))) % this.gameGenres.length;
        int funTestingTags = (((int) (Math.random() * this.gameTags.length))) % this.gameTags.length;

        String genreToSearchFor = gameGenres[funTestingGenre];
        String tagToSearchFor = gameTags[funTestingTags];
        System.out.println("Searching Genre is " + genreToSearchFor + " on page " + pageKeeperGenres.get(genreToSearchFor));
        System.out.println("Searching Tag is " + tagToSearchFor + " on page " + pageKeeperTags.get(tagToSearchFor));

        //create the game queue
        GameQueue<Game> toBeReturned = new GameQueue<>();
        
        //make the game array to call the API for game objects
        Game[] placeHolder = gameTranslator.getGamesByGenre(genreToSearchFor, pageKeeperGenres.get(genreToSearchFor));
        Game[] placeHolder2 = gameTranslator.getGamesByTag(tagToSearchFor, pageKeeperTags.get(tagToSearchFor));

        //if the genre that is searched has all been rated then move to the next page
        placeHolder = getAnArrayThatIsNotRated(placeHolder, genreToSearchFor);
        placeHolder2 = getAnArrayThatIsNotRated(placeHolder2, tagToSearchFor);

        //check each game in the array
        for (Game placeHoldingGame:placeHolder) {
            //check that the games in the array have not been rated
            if(!GameController.wasGameViewed(placeHoldingGame.getGameID())){
                //put game in the queue
                toBeReturned.offer(placeHoldingGame);
                //to prevent copies given the tag
                gamesInQueue.put(placeHoldingGame.getTitle(), placeHoldingGame.getGameID());
            }
        }
        for (Game placeHolderTag:placeHolder2) {
            //make sure the game has not been rated or is already in the queue
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
        String favoriteGenre = GameController.mostLikedGenre();
        String favoriteTag = GameController.mostLikedTag();
        //if the users favorite genre is null then the tags are also null
        if(favoriteGenre.equals("empty")){
            favoriteGenre = gameGenres[(int) (Math.random() * gameGenres.length)];
        }
        if(favoriteTag.equals("empty")){
            favoriteTag = gameTags[(int) (Math.random() * gameTags.length)];
        }
        //ensure that if the favorite tag or genre was not obtained at application start
        if(!this.pageKeeperGenres.containsKey(favoriteGenre)){
            this.pageKeeperGenres.put(favoriteGenre, 1);
        }
        if(!this.pageKeeperTags.containsKey(favoriteTag)){
            this.pageKeeperTags.put(favoriteTag, 1);
        }

        System.out.println("Favorite Genre is " + favoriteGenre);
        System.out.println("Favorite Tag is " + favoriteTag);

        Game[] placeHolder = gameTranslator.getGamesByGenre(favoriteGenre, pageKeeperGenres.get(favoriteGenre) + 1);

        //if the genre that is searched has all been rated then move to the next page
        placeHolder = getAnArrayThatIsNotRated(placeHolder, favoriteGenre);

        for (Game game : placeHolder) {
            //insure that the game has not been rated
            if(!GameController.wasGameViewed(game.getGameID())) {
                //on the first loop the recommendation game object will be set to the first element in the array
                if (recommendation == null) {
                    recommendation = game;
                }else {
                    //in the case that the recommendation game has the same tag as another game in the array, take the highest score
                    recommendation = compareGameBasedOnTag(recommendation, game, favoriteTag);
                }
            }
        }
        return recommendation;
    }

    private Game compareGameBasedOnTag(Game _game1, Game _game2, String _favoriteTag){
        //if both games have the favorite tag take the one that has the higher score
        if(_game1.hasTag(_favoriteTag) && _game2.hasTag(_favoriteTag)) {
            //check that both games have a metacritic score
            if (!_game1.getMetacriticScore().equals("null") || !_game2.getMetacriticScore().equals("null")) {
                if (Integer.parseInt(_game1.getMetacriticScore()) < Integer.parseInt(_game2.getMetacriticScore())) {
                    //game 2 has the higher score
                    return _game2;
                } else {
                    //game 1 has the higher score
                    return _game1;
                }
            }
        }
        //if the one of the games don't have the tag, check game 2, will return game two if both have it but one does not have a score
        if(_game2.hasTag(_favoriteTag)) {
            return _game2;
        }
        return _game1;
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

    private Game[] getAnArrayThatIsNotRated(Game[] _toCheck, String _searchValue){
        Game[] toDealWith = _toCheck;
        String controlSearchValue = _searchValue;
        //if the search is invalid then search a different one
        while(toDealWith[1] == null){
            //if the search is a genre
            if(pageKeeperGenres.containsKey(controlSearchValue)){
                int intGenre = (((int) (Math.random() * this.gameGenres.length))) % this.gameGenres.length;
                controlSearchValue = gameGenres[intGenre];
                System.out.println("Now Searching " + controlSearchValue + " on page " + pageKeeperGenres.get(controlSearchValue));
                toDealWith = gameTranslator.getGamesByGenre(controlSearchValue, pageKeeperGenres.get(controlSearchValue));
            }
            //if the search is a tag
            if(pageKeeperTags.containsKey(controlSearchValue)){
                int intTag = (((int) (Math.random() * this.gameTags.length))) % this.gameTags.length;
                controlSearchValue = gameTags[intTag];
                System.out.println("Now Searching " + controlSearchValue + " on page " + pageKeeperTags.get(controlSearchValue));
                toDealWith = gameTranslator.getGamesByTag(controlSearchValue, pageKeeperTags.get(controlSearchValue));
            }
        }
        //if all elements in the array are rated then go to the next page else do nothing
        while(isArrayRated(toDealWith)){
            if(pageKeeperGenres.containsKey(controlSearchValue)) {
                int pageNumber = pageKeeperGenres.get(controlSearchValue) + 1;
                pageKeeperGenres.replace(controlSearchValue, pageNumber);
                //print to the console to show that when the page has been fulled rated to go to the next page
                System.out.println("Searching Genre " + controlSearchValue + " now on page " + pageKeeperGenres.get(controlSearchValue));
                toDealWith = gameTranslator.getGamesByGenre(controlSearchValue, pageKeeperGenres.get(controlSearchValue));
            }
            if(pageKeeperTags.containsKey(controlSearchValue)){
                int pageNumber = pageKeeperTags.get(controlSearchValue) + 1;
                pageKeeperTags.replace(controlSearchValue, pageNumber);
                System.out.println("Searching Tag " + controlSearchValue + " now on page " + pageKeeperTags.get(controlSearchValue));
                toDealWith = gameTranslator.getGamesByTag(controlSearchValue, pageKeeperTags.get(controlSearchValue));
            }
        }
        //if the array has elements and has a game that is not rated then return it
        return toDealWith;
    }

    //================= SETTERS ===============
    private void setGameGenres(String[] _inputArray){
        gameGenres = _inputArray;
    }

    private void setGameTags(String[] _inputArray){
        gameTags = _inputArray;
    }

    public void setGameTranslator(GameTranslatorIntf _inputTranslator){
        gameTranslator = _inputTranslator;
    }

    private void populatePageKeepers(){
        for (String tempGenre : gameGenres) {
            pageKeeperGenres.put(tempGenre, 1);
        }
        for (String tempTags : gameTags) {
            pageKeeperTags.put(tempTags, 1);
        }
    }
}
