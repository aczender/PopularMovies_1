package com.example.andrew.popularmovies_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

public class PosterAdapter extends ArrayAdapter<Poster> {

    private static final String LOG_TAG = PosterAdapter.class.getName();

    public PosterAdapter(Context context, List<Poster> posters) {
        super(context, 0, posters);
    }
    static class ViewHolder {
        private ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        final Poster currentPoster = getItem(position);
        ViewHolder holder;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.poster_item,
            parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) listItemView.findViewById(R.id.poster_image);
            listItemView.setTag(holder);
        } else {
            holder = (ViewHolder) listItemView.getTag();
        }
        holder.image.setImageResource(0);

        Picasso.with(getContext())
                .load("http://image.tmdb.org/t/p/w185" + currentPoster.getImage())
                .into(holder.image);

        return listItemView;
    }
}