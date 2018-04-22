package com.example.andrew.popularmovies_1;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Andrew on 2018.04.04..
 */

public class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getName();

    private JsonUtils() {

    }

    public static ArrayList<Poster> fetchPosterData(String requestUrl) {

        URL url = createUrl(requestUrl);
        String jsonResponse = null;

        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making HTTP request", e);
        }
        ArrayList<Poster> posters = extractFeatureFromJson(jsonResponse);
        return posters;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;

        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the poster JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<Poster> extractFeatureFromJson(String jsonResponse) {

        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        ArrayList<Poster> posters = new ArrayList<>();

        try {

            JSONObject jsonObj = new JSONObject(jsonResponse);
            JSONArray postersArray = jsonObj.getJSONArray("results");

            if (jsonObj.has("results")) {
                postersArray = jsonObj.getJSONArray("results");
            }

            for (int i = 0; i < postersArray.length(); i++) {

                JSONObject firstPoster = postersArray.getJSONObject(i);

                String imageUrl = firstPoster.getString("poster_path");
                if (jsonObj.has("poster_path")) {
                    imageUrl = jsonObj.getString("poster_path");
                }

                Poster poster = new Poster(imageUrl);

                posters.add(poster);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the poster JSON results", e);
        }

        return posters;
    }

}