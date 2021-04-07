package src.resources;

import src.model.Game;

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
        GameQueue queue = new GameQueue();
        queue.add(new Game("Actions", 000,"E.T.", new String[]{"BAD"}, 06, 3,
                "https://upload.wikimedia.org/wikipedia/en/thumb/f/f8/Etvideogamecover.jpg/220px-Etvideogamecover.jpg"));
        queue.add(new Game("Actions", 000,"SomeThing 1", new String[]{"BAD"}, 06, 3,
                "http://cdn.shopify.com/s/files/1/0376/5420/0459/files/Nothing_Blog_-_Graph_Image_2x_c9a4ec76-256c-400d-979d-6349ca93f3f4.png?v=1611747534"));
        queue.add(new Game("Actions", 000,"Something 2", new String[]{"BAD"}, 06, 3,
                "http://cdn.shopify.com/s/files/1/0376/5420/0459/files/Nothing_Blog_-_Graph_Image_2x_c9a4ec76-256c-400d-979d-6349ca93f3f4.png?v=1611747534"));
        return queue;
    }
}
