package Testing;

/*
  unit test
  last updated 04/25/2021
  Author(s) Ian Holder,
 */

import org.json.JSONObject;
import resources.MockDataStorage;
import user.DoubledLinkList;
import user.User;

import java.util.HashMap;

import static user.SaveDataTranslator.*;
import static user.User.*;

public class Test {
    //testing method
    public static void test(){
        MockDataStorage test = new MockDataStorage();
        setDataStorage(test);
        test1();
        User.clear();
        test2();
        User.clear();

    }

    private static void test1(){
        System.out.println("Test one");
        System.out.println("Testing for an empty user object, edge case");
        //set the user data to empty
        setViewedGames(new HashMap<>());
        setUserGenres(new DoubledLinkList());
        setUserTags(new DoubledLinkList());
        //call the save to pass the JSON to test
        saveUserData();
        //empty the user obj
        User.clear();
        //repopulate the user from the JSON
        loadUserData();
        //read the user data back in
        HashMap<String,String> toBeViewed = getViewedGames();
        DoubledLinkList toBeGenre = getUserGenres();
        DoubledLinkList toBeTags = getUserTags();
        //evaluate
        if(toBeViewed.isEmpty() && toBeGenre.empty()){
            if(toBeTags.empty()){
                System.out.println("Test passed");
            }else {
                System.out.println("test failed tags don't match");
            }
        }else{
            System.out.println("Viewed Games failed or user genres failed");
        }
    }

    private static void test2(){
        System.out.println("Test two");
        System.out.println("Testing a user with 100 rated games, tags, and Genres, edge case");
        //populate the user with imposable large data
        HashMap<String,String> mockViewedGames = fillHashMap(100);
        DoubledLinkList mockedGenres = fillList(19);
        DoubledLinkList mockedTags = fillList(100);
        setUserGenres(mockedGenres);
        setUserTags(mockedTags);
        setViewedGames(mockViewedGames);
        saveUserData();
        User.clear();
        loadUserData();
        HashMap<String,String> toCheck = getViewedGames();
        DoubledLinkList genresToCheck = getUserGenres();
        DoubledLinkList tagsToCheck = getUserTags();
        if(mockViewedGames.equals(toCheck) && mockedGenres.isEqual(genresToCheck)){
            if(mockedTags.isEqual(tagsToCheck)){
                System.out.println("Test passed");
            }else{
                System.out.println("Test fail on tags check");
            }
        }else{
            System.out.println("Test fail on games viewed or genres");
        }
    }

    //helper to fill a hash map with 100 elements
    private static HashMap<String,String> fillHashMap(int _numberOfElements){
        HashMap<String,String> toReturn = new HashMap<>();
        for(int i = 0; i <= _numberOfElements; i++){
            String tempKey = String.valueOf(i);
            String tempValue = String.valueOf(i);
            toReturn.put(tempKey, tempValue);
        }
        return toReturn;
    }
    //helper to fill Double Link lists with 100 elements
    private static DoubledLinkList fillList(int _numberOfElements){
        DoubledLinkList toReturn = new DoubledLinkList();
        for(int i = 0; i <= _numberOfElements; i++){
            String tempTitle = String.valueOf(i);
            toReturn.addElement(tempTitle,i);
        }
        return toReturn;
    }
}
