package resources;

/*
  a spoof data storage for the unit test
  last updated 04/25/2021
  Author(s) Ian Holder,
 */

import org.json.JSONObject;

public class MockDataStorage implements DataStorageIntf{
    private JSONObject desiredLoadedData;

    //gets the json from the save data translator
    @Override
    public void saveFile(JSONObject _toBeStored) {
        setDesiredLoadedData(_toBeStored);
    }

    //sends a json to the save data translator
    @Override
    public JSONObject readFile() {
        return this.desiredLoadedData;
    }

    //================= SETTERS ===============

    public void setDesiredLoadedData(JSONObject _desiredLoadJSON){
        this.desiredLoadedData = _desiredLoadJSON;
    }
}
