package resources;

/*
 * to write and read jsons to/from files
 * last updated 04/27/2021
 * Author(s) Ian Holder,
 */

import org.json.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataStorage implements DataStorageIntf{
    private FileWriter fileOut;
    private static BufferedReader fileIn;
    private static final String fileName = "C:\\TestingProjectSaveFiles\\gameGenieSaveData.txt";
    private static final String canNotFindFileJSONString = "{\"viewedGames\":[],\"userTags\":[],\"userGenre\":[]}";

    //to take a json convert it to a usable form and write to a file
    public void saveFile(JSONObject _toBeStored){
        try{
            //make a path to create the directory
            Path filePath = Paths.get(fileName);
            Files.createDirectories(filePath.getParent());
            //write the file in the directory
            fileOut = new FileWriter(fileName);
            fileOut.write(_toBeStored.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //empty and close the file writer
                fileOut.flush();
                fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //to read info from a file convert it to a json and send it back
    public JSONObject readFile() {
        String inputData = null;
        JSONObject toBeReturned = new JSONObject();
        //using a string builder to piece together all the chars from the buffer reader
        File file = new File(fileName);
        if(file.exists()) {
            try {
                StringBuilder sb = new StringBuilder();

                InputStream in = new FileInputStream(file);
                fileIn = new BufferedReader(new InputStreamReader(in));
                while ((inputData = fileIn.readLine()) != null) {
                    sb.append(inputData + System.lineSeparator());
                }
                //make a json with the data stored only can work if the stored data was a json to stringed, or in that format
                toBeReturned = new JSONObject(sb.toString());
                return toBeReturned;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fileIn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            //if the file does not exist then return an empty data, hard coded which is not great
            try {
                return new JSONObject(canNotFindFileJSONString);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //if it could not read anything in it will return an empty Json
        return toBeReturned;
    }
}
