package com.example.andrew.popularmovies_1;


/**
 * Created by Andrew on 2018.03.31..
 */

public class Poster {

    private String posterId;
    private String image;
    private String title;
    private String synopsis;
    private int userRating;
    private double popularity;
    private String releaseDate;


    public Poster (String posterId, String image, String title, String synopsis, int userRating,
                 double
            popularity, String
            releaseDate) {
        this.posterId = posterId;
        this.image = image;
        this.title = title;
        this.synopsis = synopsis;
        this.userRating = userRating;
        this.popularity = popularity;
        this.releaseDate = releaseDate;
    }

    public String getPosterId() {
        return posterId;
    }
    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public int getUserRating() {
        return userRating;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}