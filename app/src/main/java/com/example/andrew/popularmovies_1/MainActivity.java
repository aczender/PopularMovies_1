package com.example.andrew.popularmovies_1;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.media.Image;
import android.net.Uri;
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
import android.widget.TextView;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        GridView gridView = findViewById(R.id.poster_gridview);

        final PosterAdapter postersAdapter = new PosterAdapter(this, new ArrayList<Poster>());
        gridView.setAdapter(postersAdapter);

    }

    /*private Poster[] posters = {
            new Poster("https://api.themoviedb" +
                    ".org/3/movie/550?api_key=abaf8cd342d71956628f640100f60e27")
    };*/

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