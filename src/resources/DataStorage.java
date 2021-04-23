package resources;

/*
 * to write and read jsons to/from files
 * last updated 04/22/2021
 * Author(s) Ian Holder,
 */

import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataStorage {
    private static FileWriter fileOut;
    private static BufferedReader fileIn;
    private static Path filePath;
    private static final String fileName = "C:\\TestingProjectSaveFiles\\gameGenieSaveData.txt";

    //to take a json convert it to a usable form and write to a file
    public static void saveFile(JSONObject _toWrite){
        try{
            filePath = Paths.get(fileName);
            Files.createDirectories(filePath.getParent());
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
            StringBuilder sb = new StringBuilder();
            InputStream in = new FileInputStream(fileName);
            fileIn = new BufferedReader(new InputStreamReader(in));
            while((inputData = fileIn.readLine()) != null){
                sb.append(inputData + System.lineSeparator());
            }
            toBeReturned = new JSONObject(sb.toString());
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
