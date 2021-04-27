package gameGenie;

/*
 * controller to handle user selections from the views
 * last updated 04/27/2021
 * Author(s) Ian Holder,
 */

import javafx.scene.control.CheckBox;
import resources.DataStorage;
import resources.Game;
import resources.UserHistoryIntf;
import user.SaveDataTranslator;
import user.GameParsing;
import user.User;

public class UserController {
    private static final int defaultLikeValue = 1;
    private static final int defaultDislikeValue = -1;

    //populate the user liked genres based on the selected check boxes from the start screen
    protected static void handleCheckBoxes(CheckBox[] _checkBoxArray){
        GameParsing.addStartScreenSelections(_checkBoxArray,defaultLikeValue);
    }

    //populate the user genres and tags, selected from the game picker screen
    protected static void Liked(Game _game){
        GameParsing.likedGame(_game,defaultLikeValue);
    }

    protected static void Disliked(Game _game){
        GameParsing.disLikedGame(_game,defaultDislikeValue);
    }

    //call the Save translator to load in save file
    protected static boolean userDataLoaded(){
        SaveDataTranslator.setDataStorage(new DataStorage());
        //call that if there is any saved data
        UserHistoryIntf newUser = SaveDataTranslator.loadUserData();
        //load that data into the user class
        User.getInstance().setUserHistory(newUser);
        //if the user class is not empty then go to the game picker
        return !User.getInstance().isEmpty();
    }

    //on close request save the user data
    protected static void programClose() {
        SaveDataTranslator.saveUserData(User.getInstance().getUserHistory());
    }

    //check if a game had been rated
    public static Boolean wasGameViewed(String _gameID){
        return User.getInstance().wasViewed(_gameID);
    }
}
