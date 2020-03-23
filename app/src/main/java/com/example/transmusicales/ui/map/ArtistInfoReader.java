package com.example.transmusicales.ui.map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArtistInfoReader {

    /*
     * This matches only once in whole input,
     * so Scanner.next returns whole InputStream as a String.
     * http://stackoverflow.com/a/5445161/2183804
     */
    private static final String REGEX_INPUT_BOUNDARY_BEGINNING = "\\A";

    public List<ArtistGeoInfo> read(InputStream inputStream) throws JSONException {
        List<ArtistGeoInfo> items = new ArrayList<>();
        String json = new Scanner(inputStream).useDelimiter(REGEX_INPUT_BOUNDARY_BEGINNING).next();
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            String title = null;
            String premiereSalle = null;
            JSONObject object = array.getJSONObject(i);
            double lat = object.getDouble("lat");
            double lng = object.getDouble("lng");
            if (!object.isNull("name")) {
                title = object.getString("name");
            }
            if (!object.isNull("premiere_salle")) {
                premiereSalle = object.getString("premiere_salle");
            }
            items.add(new ArtistGeoInfo(lat, lng, title, premiereSalle));
        }
        return items;
    }
}
