package com.example.andrew.popularmovies_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.example.andrew.popularmovies_1.JsonUtils;

import org.json.JSONArray;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private PosterAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView thumbnailIv = findViewById(R.id.thumbnail);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] details = getResources().;
        String jsonResponse = details[position];
        Poster poster = null;
        poster = JsonUtils.extractFeatureFromJson();

        if (poster == null) {
            // Poster data unavailable
            closeOnError();
            return;
        }

        populateUI(poster);
        Picasso.with(this)
                .load(poster.getImage())
                .into(thumbnailIv);

        //setTitle(poster.getMainName());
    }
    private void closeOnError() {
        finish();
        Toast.makeText(this, "Movie data not available", Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Poster poster) {

        TextView titleTv = findViewById(R.id.title_tv);
        TextView plotSynopsisTv = findViewById(R.id.plot_synopsis_tv);
        TextView userRatingTv = findViewById(R.id.user_rating_iv);
        TextView releaseDateTv = findViewById(R.id.release_date_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        titleTv.setText(poster.getTitle());
        plotSynopsisTv.setText(poster.getSynopsis());
        userRatingTv.setText(poster.getUserRating());
        releaseDateTv.setText(poster.getReleaseDate());
    }
}