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
        return new Game("Shooter", 004,"Half-Life", new String[]{"Singleplayer"}, 86, 3,
                "https://upload.wikimedia.org/wikipedia/en/f/fa/Half-Life_Cover_Art.jpg");
    }

    public static GameQueue getGameQueue(){
        //placeholder
        GameQueue queue = new GameQueue();
        queue.add(new Game("Action", 000,"E.T. the Extra-Terrestrial", new String[]{"BAD"}, 06, 3,
                "https://upload.wikimedia.org/wikipedia/en/thumb/f/f8/Etvideogamecover.jpg/220px-Etvideogamecover.jpg"));
        queue.add(new Game("Casual", 001,"Animal Crossing: New Horizons", new String[]{"Fun"}, 93, 3,
                "https://animal-crossing.com/new-horizons/assets/img/share-fb.jpg"));
        queue.add(new Game("RPG", 003,"BioShock", new String[]{"FPS"}, 80, 3,
                "https://upload.wikimedia.org/wikipedia/en/6/6d/BioShock_cover.jpg"));
        return queue;
    }
}
