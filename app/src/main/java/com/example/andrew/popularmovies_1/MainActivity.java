package com.example.andrew.popularmovies_1;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;




public class MainActivity extends AppCompatActivity implements
        LoaderCallbacks<List<Poster>> {

    private static final String LOG_TAG = MainActivity.class.getName();

    private static final String MOVIE_REQUEST_URL = "https://api.themoviedb.org/3/movie";
    private static final int POSTER_LOADER_ID = 1;
    private static final String API_KEY = "api_key";
    private static final String KEY = "abaf8cd342d71956628f640100f60e27";


    private TextView mEmptyStateTextView;

    private PosterAdapter mAdapter;

    private ProgressBar mProgressbar;
    private RecyclerView mRecyclerView;
    private String main;

    private String mQuery;

    private GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView gridView = findViewById(R.id.poster_gridview);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        gridView.setEmptyView(mEmptyStateTextView);

        final PosterAdapter postersAdapter = new PosterAdapter(this, new ArrayList<Poster>());
        gridView.setAdapter(postersAdapter);


        mAdapter = new PosterAdapter(this, new ArrayList<Poster>());

        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                mAdapter.getItem(position);
                launchDetailActivity(position);
            }
        });

        mProgressbar = (ProgressBar) findViewById(R.id.loading_indicator);

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context
                .CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(POSTER_LOADER_ID, null, this);
        } else {
            mProgressbar.setVisibility(View.GONE);
            mEmptyStateTextView.setText(R.string.no_internet);
        }
    }

    private void launchDetailActivity(int position) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_POSITION, position);
    }
    @Override
    public Loader<List<Poster>> onCreateLoader(int i, Bundle bundle) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String sortBy = sharedPrefs.getString(
                getString(R.string.settings_order_by_key),
                getString(R.string.settings_order_by_default)
        );

        Uri baseUri = Uri.parse(MOVIE_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendPath(sortBy);
        uriBuilder.appendQueryParameter(API_KEY, KEY);

        return new PosterLoader(this, uriBuilder.toString());
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
            Toast.makeText(getApplicationContext(), R.string.no_poster, Toast.LENGTH_SHORT).show();
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

        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
