package resources;

import org.json.JSONObject;

public interface DataStorageIntf {
    public void saveFile(JSONObject _toBeStored);
    public JSONObject readFile();
}
