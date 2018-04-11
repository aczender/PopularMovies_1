package com.example.andrew.popularmovies_1;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Andrew on 2018.04.04..
 */

public class JsonUtils {

    public static Poster parsePosterJson(String json) {
        try {
            JSONObject root = new JSONObject(json);
            String image = root.getString("image");

            return new Poster(image);
        } catch (JSONException e) {
            Log.e("JsonUtils", "Problem parsing the movies JSON results", e);
        }
        return null;
    }
}
