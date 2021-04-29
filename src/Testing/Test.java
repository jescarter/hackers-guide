package Testing;

/*
  unit test
  last updated 04/27/2021
  Author(s) Ian Holder,
 */

import DataStorage.MockDataStorage;
import user.UserHistory;

import java.util.HashMap;

import static DataStorage.SaveDataTranslator.*;

public class Test {
    //testing method
    public static void test(){
        MockDataStorage test = new MockDataStorage();
        setDataStorage(test);
        test1();
        test2();
    }

    private static void test1(){
        System.out.println("Test one");
        System.out.println("Testing for an empty user object, edge case");
        //set the user data to empty
        UserHistory testingUser = new UserHistory();
        //call the save to pass the JSON to test
        saveUserData(testingUser);
        //repopulate the user from the JSON
        UserHistory toCompare = (UserHistory) loadUserData();
        //read the user data back in
        HashMap<String, String> toBeViewed = toCompare.getViewedGames();
        HashMap<String, Integer> toBeGenre = toCompare.getUserGenres();
        HashMap<String, Integer> toBeTags = toCompare.getUserTags();
        //evaluate
        if(toBeViewed.isEmpty() && toBeGenre.isEmpty()){
            if(toBeTags.isEmpty()){
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
        System.out.println("Testing a user with 100 rated games, 100 tags, and 19 genres, edge case");
        UserHistory toFill = new UserHistory();
        //populate the user with imposable large data
        HashMap<String,String> mockViewedGames = fillHashMapString(100);
        HashMap<String, Integer> mockedGenres = fillHashMapInt(19);
        HashMap<String, Integer> mockedTags = fillHashMapInt(100);
        toFill.setUserGenres(mockedGenres);
        toFill.setUserTags(mockedTags);
        toFill.setViewedGames(mockViewedGames);
        saveUserData(toFill);

        UserHistory toCompare =(UserHistory) loadUserData();
        if(toFill.isEqual(toCompare)){
            System.out.println("Test passed");
        }else{
            System.out.println("Test Failed");
        }
    }

    //helper to fill a hash map with 100 elements
    private static HashMap<String,String> fillHashMapString(int _numberOfElements){
        HashMap<String,String> toReturn = new HashMap<>();
        for(int i = 0; i <= _numberOfElements; i++){
            String tempKey = String.valueOf(i);
            String tempValue = String.valueOf(i);
            toReturn.put(tempKey, tempValue);
        }
        return toReturn;
    }
    private static HashMap<String,Integer> fillHashMapInt(int _numberOfElements){
        HashMap<String,Integer> toReturn = new HashMap<>();
        for(int i = 0; i <= _numberOfElements; i++){
            String tempKey = String.valueOf(i);
            toReturn.put(tempKey, i);
        }
        return toReturn;
    }
}
