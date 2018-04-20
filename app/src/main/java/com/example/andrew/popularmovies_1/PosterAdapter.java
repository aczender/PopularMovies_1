package com.example.andrew.popularmovies_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PosterAdapter extends ArrayAdapter<Poster> {

    private static class ViewHolder {
        ImageView image;
    }

    // 1
    public PosterAdapter(Context context, ArrayList<Poster> posters) {
        super(context, R.layout.poster_item, posters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Poster poster = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.poster_item, parent, false);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.poster_image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.image.setImageResource(poster.image);

        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185/").into(viewHolder
                .image);

        return convertView;
    }
}
            /*final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.poster_item, null);

            final ImageView posterImage = (ImageView) convertView.findViewById(R.id.poster_image);

            final ViewHolder viewHolder = new ViewHolder(posterImage);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        viewHolder.posterImage.setImageResource(0);

        Picasso.with(currentPoster).load(poster.getImage()).into(viewHolder.posterImage);

        return convertView;
    }

    private class ViewHolder {
        private final ImageView posterImage;

        public ViewHolder(ImageView posterImage) {
            this.posterImage = posterImage;
        }
    }
}*/