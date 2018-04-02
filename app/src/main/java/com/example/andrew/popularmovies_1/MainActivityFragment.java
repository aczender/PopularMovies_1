package com.example.andrew.popularmovies_1;

import android.app.Fragment;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrew on 2018.04.01..
 */

public class MainActivityFragment extends Fragment {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private PosterAdapter adapter;

    Poster[] posters = {
            new Poster("Interstellar", R.drawable.inters)
    };

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        adapter = new PosterAdapter(getActivity(), Arrays.asList(posters));

        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view_image_text);
        gridView.setAdapter(adapter);

        return rootView;
    }

}