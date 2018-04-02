package com.example.andrew.popularmovies_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Andrew on 2018.03.31..
 */

public class Poster {
    String posterTitle;
    int image;

    public Poster (String pTitle, int image) {
        this.posterTitle = pTitle;
        this.image = image;
    }
}