package resources;

/*
 * helpers
 * last updated 04/06/2021
 * Author(s) Ian Holder,
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Util {
    public static final String[] genresArray = {"Action", "Indie", "Adventure", "RPG", "Strategy", "Shooter", "Casual",
            "Simulation", "Puzzle", "Arcade", "Platformer", "Racing", "Sports", "Massively Multiplayer", "Fighting",
            "Family", "Board Games", "Educational", "Card"};

    //helper for the data storage
    public static HashMap<String,Integer> fromLinkListToMap(DoubledLinkList _inputList){
        HashMap<String,Integer> toReturn = new HashMap<>();
        DoubledLinkList.Node current = _inputList.head;
        while(current != _inputList.tail){
            toReturn.put(current.nodeTitle, current.preferenceValue);
            current = current.next;
        }
        return toReturn;
    }

    public static DoubledLinkList fromMapToLinkList(HashMap<String,Integer> _inputMap){
        DoubledLinkList toBeReturned = new DoubledLinkList();
        Iterator inputMapIterator = _inputMap.entrySet().iterator();
        while(inputMapIterator.hasNext()){
            Map.Entry mapElement = (Map.Entry)inputMapIterator.next();
            toBeReturned.addElement((String)mapElement.getKey(),(int)mapElement.getValue());
        }
        return toBeReturned;
    }
}
