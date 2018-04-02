package com.example.andrew.popularmovies_1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.Arrays;

public class MainFragment extends FragmentActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private PosterAdapter adapter;

    Poster[] posters = {
            new Poster("Interstellar", R.drawable.inters)
    };

    public MainFragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);


        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view_image_text);
        gridView.setAdapter(adapter);

        return rootView;
    }

}