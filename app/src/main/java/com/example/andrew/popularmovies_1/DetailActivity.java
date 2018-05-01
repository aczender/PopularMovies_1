package com.example.andrew.popularmovies_1;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private Poster mPoster;
    ImageView thumbnailIv;
    TextView titleTv;
    TextView plotSynopsisTv;
    TextView userRatingTv;
    TextView releaseDateTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //final PosterAdapter postersAdapter = new PosterAdapter(this, new ArrayList<Poster>());
        //thumbnailIv.setTag(postersAdapter);

        if (getIntent().hasExtra(EXTRA_POSITION)) {
            mPoster = getIntent().getParcelableExtra(EXTRA_POSITION);
        } else {
            throw new IllegalArgumentException("Detail activity must receive a movie parcelable");
        }

        thumbnailIv = (ImageView) findViewById(R.id.thumbnail);
        titleTv = (TextView) findViewById(R.id.title_tv);
        plotSynopsisTv = (TextView) findViewById(R.id.plot_synopsis_tv);
        userRatingTv = (TextView) findViewById(R.id.user_rating_iv);
        releaseDateTv = (TextView) findViewById(R.id.release_date_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        Picasso.with(this)
                .load(mPoster.getImage())
                .into(thumbnailIv);
        titleTv.setText(mPoster.getTitle());
        plotSynopsisTv.setText(mPoster.getSynopsis());
        userRatingTv.setText(mPoster.getUserRating());
        releaseDateTv.setText(mPoster.getReleaseDate());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, "Movie data not available", Toast.LENGTH_SHORT).show();
    }
}