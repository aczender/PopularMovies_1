package com.example.andrew.popularmovies_1;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.ParcelUuid;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Poster>>{

    private static final String LOG_TAG = MainActivity.class.getName();

    private static final String POSTER_REQUEST_URL = "https://api.themoviedb" +
            ".org/3/movie/550?";

    private static final int POSTER_LOADER_ID = 1;

    private static final String API_KEY = "api_key";

    private static final String KEY = "abaf8cd342d71956628f640100f60e27";

    private String mQuery;

    private GridView gridView;

    private TextView mEmptyStateTextView;

    private PosterAdapter mAdapter;

    private ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GridView gridView = findViewById(R.id.poster_gridview);

        final PosterAdapter postersAdapter = new PosterAdapter(this, new ArrayList<Poster>());
        gridView.setAdapter(postersAdapter);

        View v = getLayoutInflater().inflate(R.layout.poster_item, null);

        ImageView posterImage = (ImageView) v.findViewById(R.id.poster_image);

        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w185/")
                .into(posterImage);

        mProgressbar = (ProgressBar) findViewById(R.id.loading_indicator);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);

        if(isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(POSTER_LOADER_ID, null, null);
        } else {
            mProgressbar.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);
        }

    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.sort_popular) {
            return true;
        }
        if (id == R.id.sort_rate) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<List<Poster>> onCreateLoader(int i, Bundle bundle) {
        String requestUrl = "";
        if (mQuery != null && mQuery != "") {
            requestUrl = POSTER_REQUEST_URL + mQuery;
        } else {
            String defaultQuery = "";
            requestUrl = POSTER_REQUEST_URL + defaultQuery;
        }
        return new PosterLoader(this, requestUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Poster>> loader, List<Poster> posters) {
        mEmptyStateTextView.setText(R.string.no_poster);
        mAdapter.clear();

        if (posters != null && !posters.isEmpty()) {
            mAdapter.addAll(posters);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Poster>> loader) {
        mAdapter.clear();
    }
}