package com.example.andrew.popularmovies_1;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class PosterLoader extends AsyncTaskLoader<List<Poster>> {

    private static final String LOG_TAG = PosterLoader.class.getName();

    private String mUrl;

    public PosterLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Poster> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        List<Poster> posters = JsonUtils.fetchPosterData(mUrl);
        return posters;
    }
}
