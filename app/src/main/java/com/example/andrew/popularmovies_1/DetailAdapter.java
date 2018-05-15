package com.example.andrew.popularmovies_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailAdapter extends ArrayAdapter<Poster> {

    private static final String LOG_TAG = DetailAdapter.class.getName();

    public DetailAdapter (Context context, List<Poster> posters) {
        super(context, 0, posters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View lisItemView = convertView;
        final Poster currentPoster = getItem(position);

        if (lisItemView == null) {
            lisItemView = LayoutInflater.from(getContext()).inflate(R.layout.detail_activity, parent,
                    false);
        }

        ImageView thumbnailView = (ImageView) lisItemView.findViewById(R.id.thumbnail);
        if (currentPoster.getImage() != null)
        Picasso.with(getContext())
                .load("http://image.tmdb.org/t/p/w185" + currentPoster.getImage())
                .into(thumbnailView);

        // Find the TextView with view ID title
        TextView titleView = (TextView) lisItemView.findViewById(R.id.title_tv);
        if (currentPoster.getTitle() != null)
            titleView.setText(currentPoster.getTitle());
        TextView synopsisView = (TextView) lisItemView.findViewById(R.id.plot_synopsis_tv);
        if (currentPoster.getSynopsis() != null)
            synopsisView.setText(currentPoster.getSynopsis());
        // TextView ratingView = (TextView) lisItemView.findViewById(R.id.user_rating_iv);
        //if (currentPoster.getUserRating() != null)
        //  ratingView.setText(currentPoster.getUserRating());
        TextView releaseView = (TextView) lisItemView.findViewById(R.id.release_date_tv);
        if (currentPoster.getReleaseDate() != null)
            releaseView.setText(currentPoster.getReleaseDate());

        return lisItemView;
    }
}
