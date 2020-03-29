package com.example.transmusicales.ui.map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
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
        JSONObject jsonObject = new JSONObject(json);
        JSONObject artistes = jsonObject.getJSONObject("artistes");
        for (Iterator<String> it = artistes.keys(); it.hasNext(); ) {
            String key = it.next();
            String title = null;
            try {
                JSONObject artiste = artistes.getJSONObject(key);
                JSONObject fields = artiste.getJSONObject("fields");
                JSONObject geometry = artiste.getJSONObject("geometry");
                JSONArray coordinates = geometry.getJSONArray("coordinates");

                double lng = coordinates.getDouble(0);
                double lat = coordinates.getDouble(1);
                if (!fields.isNull("name")) {
                    title = fields.getString("name");
                }
                items.add(new ArtistGeoInfo(lat, lng, title));
            }
            catch (JSONException e) {
                System.out.println("Error while reading artiste " + key);
            }
        }
        return items;
    }
}
