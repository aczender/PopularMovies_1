package com.example.andrew.popularmovies_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PosterAdapter extends ArrayAdapter<Poster> {

    private static final String LOG_TAG = PosterAdapter.class.getName();

    private final Context context;
    private final List<Poster> posters;

    public PosterAdapter(Context context, List<Poster> posters) {
        super(context, 0, posters);
        this.context = context;
        this.posters = posters;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final Poster posters = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.poster_item,
                    parent, false);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.poster_image);
        Picasso.with(getContext())
                .load("http://image.tmdb.org/t/p/w185" + posters.getImage())
                .into(imageView);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle extras = new Bundle();
                extras.putString("title", posters.getTitle());
                extras.putString("thumbnail", posters.getImage());
                extras.putString("synopsis", posters.getSynopsis());
                extras.putInt("rating", posters.getUserRating());
                extras.putString("releaseDate", posters.getReleaseDate());
                intent.putExtras(extras);

                context.startActivity(intent);

            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public int getPosition(Poster item) {
        return super.getPosition(item);
    }

}