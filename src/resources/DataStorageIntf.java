package resources;

import org.json.JSONObject;

public interface DataStorageIntf {
    public void saveFile(JSONObject toBeStored);
    public JSONObject readFile();
}
