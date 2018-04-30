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
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Poster>>{

    private static final String LOG_TAG = MainActivity.class.getName();

    private static final String POSTER_REQUEST_URL = "http://api.themoviedb" +
            ".org/3/movie/popular?api_key=abaf8cd342d71956628f640100f60e27";

    private static final int POSTER_LOADER_ID = 1;

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


        mAdapter = new PosterAdapter(this, new ArrayList<Poster>());

        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                launchDetailActivity(position);
            }
        });

        mProgressbar = (ProgressBar) findViewById(R.id.loading_indicator);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);

        if(isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(POSTER_LOADER_ID, null, this);
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
        mProgressbar.setVisibility(View.GONE);
        mAdapter.clear();

        if (posters != null && !posters.isEmpty()) {
            mAdapter.addAll(posters);
        }
    }
    protected void onPostExecute(Loader<List<Poster>> loader, List<Poster> posters) {
        if (posters != null) {
            Toast.makeText(getApplicationContext(),R.string.no_poster, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Poster>> loader) {
        mAdapter.clear();
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

    private void launchDetailActivity(int position) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
        startActivity(intent);
    }
}