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

    static class ViewHolder {
        private ImageView image;
    }

    // 1
    public PosterAdapter(Context context, ArrayList<Poster> posters) {
        super(context, 0, posters);
    }

   /* // 2
    @Override
    public int getCount() {
        return posters.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }*/

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        final Poster currentPoster = getItem(position);
        ViewHolder holder;


        if (convertView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.poster_item, parent,
            false);

            holder = new ViewHolder();
            holder.image = (ImageView) listItemView.findViewById(R.id.poster_image);
        } else {
            holder = (ViewHolder) listItemView.getTag();
        }

        holder.image.setImageResource(currentPoster.getImage());

        return listItemView;
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