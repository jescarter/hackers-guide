package model;

import resources.DoubledLinkList;

/**
 * the user to hold user selections and
 * last updated 03/28/2021
 * Author(s) Ian Holder,
 */

public class User {
    private static DoubledLinkList userGenres = new DoubledLinkList();
    private static DoubledLinkList userTags = new DoubledLinkList();

    public static void addGenreLike(String _genreName){
        userGenres.addElement(_genreName, 1);
    }
    public static void addGenreDisliked(String _genreName){
        userGenres.addElement(_genreName, -1);
    }
    public static void addTagLiked(String _tagName){
        userTags.addElement(_tagName, 1);
    }
    public static void addTagDisliked(String _tagName){
        userTags.addElement(_tagName, -1);
    }
}
