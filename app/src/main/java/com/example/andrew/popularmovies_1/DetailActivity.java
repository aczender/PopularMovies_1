package com.example.andrew.popularmovies_1;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.graphics.Movie;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

import org.parceler.Parcels;

public class DetailActivity extends AppCompatActivity {

    private static final String LOG_TAG = DetailActivity.class.getName();

    private static final String MOVIE_REQUEST_URL = "http://api.themoviedb" +
            ".org/3/movie/popular?api_key=abaf8cd342d71956628f640100f60e27";

    public static final String EXTRA_POSITION = "extra_position";

    private ProgressBar dProgressbar;

    private String mQuery;

    private Poster mPoster;

    private ImageView mThumbnail;
    private TextView mTitle;
    private TextView mSynopsis;
    private TextView mRelease;

    //private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);


        if (getIntent().hasExtra(EXTRA_POSITION)) {
            mPoster = getIntent().getParcelableExtra(EXTRA_POSITION);
        } else {
            throw new IllegalArgumentException("Parcelable");
        }


        mThumbnail = (ImageView) findViewById(R.id.thumbnail);
        mTitle = (TextView) findViewById(R.id.title);
        mSynopsis = (TextView) findViewById(R.id.synopsis);
        mRelease = (TextView) findViewById(R.id.releaseDate);

        mTitle.setText(mPoster.getTitle());
        mSynopsis.setText(mPoster.getSynopsis());
        mRelease.setText(mPoster.getReleaseDate());
        Picasso.with(this)
                .load(mPoster.getImage())
                .into(mThumbnail);
    }
}