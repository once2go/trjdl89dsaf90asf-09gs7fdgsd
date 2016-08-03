package com.once2go.model.movies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by once2go on 03.08.16.
 */
public class Image {

    @SerializedName("full")
    private String mFull;

    @SerializedName("medium")
    private String mMedium;

    @SerializedName("thumb")
    private String mThumb;


    public String getFull() {
        return mFull;
    }

    public String getMedium() {
        return mMedium;
    }

    public String getThumb() {
        return mThumb;
    }
}
