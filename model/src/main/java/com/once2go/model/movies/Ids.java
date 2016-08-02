package com.once2go.model.movies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by once2go on 01.08.16.
 */
public class Ids {

    @SerializedName("trakt")
    private int mTrakt;

    @SerializedName("slug")
    private String mSlug;

    @SerializedName("imdb")
    private String mImdb;

    @SerializedName("tmdb")
    private int mTmdb;


    public int getTrakt() {
        return mTrakt;
    }

    public String getSlug() {
        return mSlug;
    }

    public String getImdb() {
        return mImdb;
    }

    public int getTmdb() {
        return mTmdb;
    }
}
