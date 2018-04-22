package com.example.andrew.popularmovies_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class PosterAdapter extends ArrayAdapter<Poster> {

    static class ViewHolder {
        private ImageView image;
    }

    public PosterAdapter(Context context, ArrayList<Poster> posters) {
        super(context, 0, posters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View lisItemView = convertView;
        final Poster currentPoster = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            lisItemView = LayoutInflater.from(getContext()).inflate(R.layout.poster_item, parent,
                    false);
            holder = new ViewHolder();
            holder.image = (ImageView) lisItemView.findViewById(R.id.poster_image);
            lisItemView.setTag(holder);
        } else {
            holder = (ViewHolder) lisItemView.getTag();
        }
        holder.image.setImageResource(Integer.parseInt(currentPoster.getImage()));

        Picasso.with(getContext())
                .load("http://image.tmdb.org/t/p/w185/")
                .into((Target) currentPoster);

        return lisItemView;
    }
}