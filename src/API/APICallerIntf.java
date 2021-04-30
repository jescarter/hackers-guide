package API;

/*
 * to standardize api callers
 * last updated 04/27/2021
 * Author(s) Ian Holder,
 */

import org.json.JSONObject;

public interface APICallerIntf {
    public JSONObject getGamesByGenre(String _genre, int _page);

    public JSONObject getGamesByTag(String _tag, int _page);

    public JSONObject getGenres();

    public JSONObject getTags();
}
