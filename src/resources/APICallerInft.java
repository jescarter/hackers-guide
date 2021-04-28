package resources;

/*
 * to standardize api callers
 * last updated 04/27/2021
 * Author(s) Ian Holder,
 */

import org.json.JSONObject;

public interface APICallerInft {
    public JSONObject getGamesByGenre(String _genre);

    public JSONObject getGamesByTag(String _tag);

    public JSONObject getGenres();

    public JSONObject getTags();
}
