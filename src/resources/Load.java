package resources;

import Model.Game;

/**
 * helper class for game recommendations and game picker
 * last updated 04/06/2021
 * Author(s) Ian Holder,
 */

public class Load {
    //get a game that has the top tag and genera
    public static Game getRecommendation(){
        //current palace holder
        return new Game("Actions", 000,"E.T.", new String[]{"BAD"}, 06, 3,
                "https://upload.wikimedia.org/wikipedia/en/thumb/f/f8/Etvideogamecover.jpg/220px-Etvideogamecover.jpg");
    }

    public static GameQueue getGameQueue(){
        //placeholder
        return new GameQueue();
    }
}
