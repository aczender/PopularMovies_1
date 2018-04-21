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
import com.squareup.picasso.Target;

import java.util.ArrayList;

import static com.example.andrew.popularmovies_1.R.layout.poster_item;

public class PosterAdapter extends ArrayAdapter<Poster> {

    static class ViewHolder {
        private TextView imageTextView;
    }

    // 1
    public PosterAdapter(Context context, ArrayList<Poster> posters) {
        super(context, 0, posters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View lisItemView = convertView;
        Poster poster = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            lisItemView = LayoutInflater.from(getContext()).inflate(R.layout.poster_item,parent,
                    false);

            viewHolder = new ViewHolder();
            viewHolder.imageTextView = (TextView) lisItemView.findViewById(R.id.poster_image);
            lisItemView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) lisItemView.getTag();
        }

        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185/").into
                (R.layout.poster_item);

        viewHolder.imageTextView.setText(poster.image);

        return lisItemView;
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