package DataStorage;

import org.json.JSONObject;

import java.io.FileNotFoundException;

public interface DataStorageIntf {
    public void saveFile(JSONObject _toBeStored);
    public JSONObject readFile();
}
