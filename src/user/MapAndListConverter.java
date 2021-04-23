package user;

import java.util.HashMap;
import java.util.Map;

class MapAndListConverter {
    //helper for the data storage
    public static HashMap<String,Integer> fromLinkListToMap(DoubledLinkList _inputList){
        HashMap<String,Integer> toReturn = new HashMap<>();
        DoubledLinkList.Node current = _inputList.head;
        while(current != null){
            toReturn.put(current.nodeTitle, current.preferenceValue);
            current = current.next;
        }
        return toReturn;
    }

    public static DoubledLinkList fromMapToLinkList(HashMap<String,Integer> _inputMap){
        DoubledLinkList toBeReturned = new DoubledLinkList();
        for (Map.Entry<String, Integer> stringIntegerEntry : _inputMap.entrySet()) {
            toBeReturned.addElement(stringIntegerEntry.getKey(), stringIntegerEntry.getValue());
        }
        return toBeReturned;
    }
}
