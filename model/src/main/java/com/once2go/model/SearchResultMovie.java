package com.once2go.model;

import com.google.gson.annotations.SerializedName;
import com.once2go.model.movies.ReachMovie;

/**
 * Created by once2go on 04.08.16.
 */
public class SearchResultMovie {

    @SerializedName("type")
    private String mType;

    @SerializedName("score")
    private double mScore;

    @SerializedName("movie")
    private ReachMovie mMovie;

    public String getType() {
        return mType;
    }

    public double getScore() {
        return mScore;
    }

    public ReachMovie getMovie() {
        return mMovie;
    }
}
