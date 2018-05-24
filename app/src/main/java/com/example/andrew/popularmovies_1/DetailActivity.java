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
import android.support.v7.widget.Toolbar;
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

    public static final String EXTRA_POSITION = "extra_position";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String thumbnail = intent.getStringExtra("thumbnail");
        String synopsis = intent.getStringExtra("synopsis");
        int rating = intent.getIntExtra("rating", 1);
        String releaseDate = intent.getStringExtra("releaseDate");

        TextView mTitle = (TextView) findViewById(R.id.title);
        TextView mSynopsis = (TextView) findViewById(R.id.synopsis);
        TextView mRating = (TextView) findViewById(R.id.rating);
        TextView mReleaseDate = (TextView) findViewById(R.id.releaseDate);

        mTitle.setText(title);
        mSynopsis.setText(synopsis);
        mRating.setText("" + rating);
        mReleaseDate.setText(releaseDate);

        ImageView mThumbnail = (ImageView) findViewById(R.id.thumbnail);
        Picasso.with(getBaseContext())
                .load("http://image.tmdb.org/t/p/w185" + thumbnail)
                .into(mThumbnail);


    }
}