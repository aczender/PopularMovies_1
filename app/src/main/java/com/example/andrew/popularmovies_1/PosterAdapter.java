package com.example.andrew.popularmovies_1;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrew on 2018.04.01..
 */

public class PosterAdapter extends ArrayAdapter<Poster> {

    private static final String LOG_TAG = PosterAdapter.class.getSimpleName();

    public PosterAdapter(Activity context, List<Poster> posters) {

        super(context, 0, posters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Poster poster = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_main_fragment, parent,
                    false);
        }

        ImageView iconView = (ImageView) convertView.findViewById(R.id.gridview_image);
        iconView.setImageResource(poster.image);

        TextView versionNameView = (TextView) convertView.findViewById(R.id.gridview_text);
        versionNameView.setText(poster.posterTitle);

        return convertView;
    }
}