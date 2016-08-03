package com.once2go.model.movies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by once2go on 03.08.16.
 */
public class MovieImages {

    @SerializedName("fanart")
    private Image mFanArt;

    @SerializedName("poster")
    private Image mPoster;

    @SerializedName("logo")
    private Image mLogo;

    @SerializedName("clearart")
    private Image mClearArt;

    @SerializedName("thumb")
    private Image mThumb;

    public Image getFanArt() {
        return mFanArt;
    }

    public Image getPoster() {
        return mPoster;
    }

    public Image getLogo() {
        return mLogo;
    }

    public Image getClearArt() {
        return mClearArt;
    }

    public Image getThumb() {
        return mThumb;
    }
}
