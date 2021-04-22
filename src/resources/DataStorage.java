package resources;

/*
 * to write and read jsons to/from files
 * last updated 04/22/2021
 * Author(s) Ian Holder,
 */

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class DataStorage {
    private static FileWriter fileOut;
    private static BufferedReader fileIn;
    private static final String fileName = "/Users/Shared/gameGenieSaveData.txt";
    //to take a json convert it to a usable form and write to a file
    public static void saveFile(JSONObject _toWrite){
        try{
            fileOut = new FileWriter(fileName);
            fileOut.write(_toWrite.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fileOut.flush();
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //to read info from a file convert it to a json and send it back
    public static JSONObject readFile(){
        String inputData = null;
        JSONObject toBeReturned = new JSONObject();
        try{
            fileIn = new BufferedReader(new FileReader(fileName));
            while(fileIn.readLine() != null){
                inputData += fileIn.readLine();
            }
            toBeReturned = new JSONObject(inputData);
            return toBeReturned;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fileIn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return toBeReturned;
    }
}
