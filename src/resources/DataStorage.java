package resources;

/*
 * to write and read jsons to/from files
 * last updated 04/22/2021
 * Author(s) Ian Holder,
 */

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class DataStorage {
    private static FileWriter file;
    //to take a json convert it to a usable form and write to a file
    public static void saveFile(JSONObject _toWrite){
        try{
            file = new FileWriter("/Users/Shared/gameGenieSaveData.txt");
            file.write(_toWrite.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                file.flush();
                file.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //to read info from a file convert it to a json and send it back
    public static JSONObject readFile(){
        String inputData = new String();
        JSONObject toBeReturned = new JSONObject();
        try{
            File tempFile = new File("/Users/Shared/gameGenieSaveData.txt");
            Scanner reader = new Scanner(tempFile);
            while(reader.hasNextLine()){
                inputData += reader.nextLine();
                toBeReturned = new JSONObject(inputData);
                return toBeReturned;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return toBeReturned;
    }
}
