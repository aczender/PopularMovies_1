package com.example.andrew.popularmovies_1;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DetailActivity extends AppCompatActivity implements LoaderCallbacks<List<Poster>> {

    private static final String LOG_TAG = DetailActivity.class.getName();

    private static final String MOVIE_REQUEST_URL = "http://api.themoviedb" +
            ".org/3/movie/popular?api_key=abaf8cd342d71956628f640100f60e27";

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private static final int POSTER_LOADER_ID = 1;

    private DetailAdapter dAdapter;

    private ProgressBar mProgressbar;

    private String mQuery;

    private ImageView mThumbnail;
    private TextView mTitle;
    private TextView mSynopsis;
    private TextView mRelease;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        ListView listView = findViewById(R.id.detail_elements);


        final DetailAdapter detailsAdapter = new DetailAdapter(this, new ArrayList<Poster>());
        listView.setAdapter(detailsAdapter);


        dAdapter = new DetailAdapter(this, new ArrayList<Poster>());

        listView.setAdapter(dAdapter);


        mProgressbar = (ProgressBar) findViewById(R.id.loading_indicator);


        if (isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(POSTER_LOADER_ID, null, this);
        } else {
            mProgressbar.setVisibility(View.GONE);
        }
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    @Override
    public Loader<List<Poster>> onCreateLoader(int i, Bundle bundle) {
        String requestUrl = "";
        if (mQuery != null && mQuery != "") {
            requestUrl = MOVIE_REQUEST_URL + mQuery;
        } else {
            String defaultQuery = "";
            requestUrl = MOVIE_REQUEST_URL + defaultQuery;
        }
        return new PosterLoader(this, requestUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Poster>> loader, List<Poster> posters) {
        //mProgressbar.setVisibility(View.GONE);
        dAdapter.clear();

        if (posters != null && !posters.isEmpty()) {
            dAdapter.addAll(posters);
        }
    }

    protected void onPostExecute(Loader<List<Poster>> loader, List<Poster> posters) {
        if (posters != null) {
            Toast.makeText(getApplicationContext(), R.string.no_poster, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Poster>> loader) {
        dAdapter.clear();
    }

}
