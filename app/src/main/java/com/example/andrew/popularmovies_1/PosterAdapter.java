package com.example.andrew.popularmovies_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PosterAdapter extends BaseAdapter {

    private final Context mContext;
    private final Poster[] posters;

    // 1
    public PosterAdapter(Context context, Poster[] posters) {
        this.mContext = context;
        this.posters = posters;
    }

    // 2
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
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Poster poster = posters[position];


        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.poster_item, null);

            final ImageView posterImage = (ImageView) convertView.findViewById(R.id.poster_image);

            final ViewHolder viewHolder = new ViewHolder(posterImage);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        viewHolder.posterImage.setImageResource(poster.getImage());

        Picasso.with(mContext).load(poster.getImage()).into(viewHolder.posterImage);

        return convertView;
    }

    private class ViewHolder {
        private final ImageView posterImage;

        public ViewHolder(ImageView posterImage) {
            this.posterImage = posterImage;
        }
    }
}