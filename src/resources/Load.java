package resources;

import model.Game;

/**
 * helper class for game recommendations and game picker
 * last updated 04/08/2021
 * Author(s) Ian Holder,
 */

public class Load {
    //TODO that will return if there was any data to the user class/object and load said data
    public static boolean userDataLoaded(){
        return false;
    }


    //TODO get a game that has the top tag and genera
    public static Game getRecommendation(){
        //current palace holder
        return new Game("Shooter", 004,"Half-Life", new String[]{"Singleplayer"}, 86,
                "https://upload.wikimedia.org/wikipedia/en/f/fa/Half-Life_Cover_Art.jpg", "1995", new String[]{"PC"},123);
    }

    //TODO that will populate the game queue with games from the API
    public static GameQueue getGameQueue(){
        //placeholder
        GameQueue queue = new GameQueue();
        queue.add(new Game("Action", 000,"E.T. the Extra-Terrestrial", new String[]{"BAD"}, 06,
                "https://upload.wikimedia.org/wikipedia/en/thumb/f/f8/Etvideogamecover.jpg/220px-Etvideogamecover.jpg", "2001", new String[]{"Atari 2600"}, 001));
        queue.add(new Game("Casual", 001,"Animal Crossing: New Horizons", new String[]{"Fun"}, 93,
                "https://animal-crossing.com/new-horizons/assets/img/share-fb.jpg", "2020", new String[]{"Switch"}, 789));
        queue.add(new Game("RPG", 003,"BioShock", new String[]{"FPS"}, 80,
                "https://upload.wikimedia.org/wikipedia/en/6/6d/BioShock_cover.jpg", "2008", new String[]{"PS3", "PC"}, 456));
        return queue;
    }
}
