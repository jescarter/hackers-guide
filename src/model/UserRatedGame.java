package model;

import static model.User.*;

/**
 * A helper to process user selections from the game picker screen
 * last updated 04/13/2021
 * Author(s) Ian Holder,
 */

public class UserRatedGame {
    public static void Liked(Game _game){
        for (String genre: _game.genre) {
            addGenreLike(genre);
        }
        for (String tag: _game.tags) {
            addTagLiked(tag);
        }
    }
    public static void Disliked(Game _game){
        for (String tag:_game.tags) {
            addTagDisliked(tag);
        }
    }
}
