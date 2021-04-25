package resources;

/*
  a spoof data storage for the unit test
  last updated 04/25/2021
  Author(s) Ian Holder,
 */

import org.json.JSONObject;

public class MockDataStorage implements DataStorageIntf{
    private JSONObject desiredSaveFileJSON;
    private JSONObject desiredLoadedData;

    //gets the json from the save data translator
    @Override
    public void saveFile(JSONObject _toBeStored) {
        //for the ease saying that if the JSON strings are the same then the jsons are the same
        if(_toBeStored.toString().equals(this.desiredSaveFileJSON.toString())){
            System.out.println("Test Passed, save file JSON matched");
        }else{
            System.out.println("Test Failed");
            System.out.println(this.desiredSaveFileJSON);
            System.out.println(_toBeStored);
        }
    }

    //sends a json to the save data translator
    @Override
    public JSONObject readFile() {
        return this.desiredLoadedData;
    }

    //================= SETTERS ===============

    public void setDesiredSaveFileJSON(JSONObject _desiredSaveJSON){
        this.desiredSaveFileJSON = _desiredSaveJSON;
    }

    public void setDesiredLoadedData(JSONObject _desiredLoadJSON){
        this.desiredLoadedData = _desiredLoadJSON;
    }
}
