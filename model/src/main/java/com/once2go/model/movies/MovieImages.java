package com.once2go.model.movies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by once2go on 03.08.16.
 */
public class MovieImages implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mFanArt, flags);
        dest.writeParcelable(this.mPoster, flags);
        dest.writeParcelable(this.mLogo, flags);
        dest.writeParcelable(this.mClearArt, flags);
        dest.writeParcelable(this.mThumb, flags);
    }

    public MovieImages() {
    }

    protected MovieImages(Parcel in) {
        this.mFanArt = in.readParcelable(Image.class.getClassLoader());
        this.mPoster = in.readParcelable(Image.class.getClassLoader());
        this.mLogo = in.readParcelable(Image.class.getClassLoader());
        this.mClearArt = in.readParcelable(Image.class.getClassLoader());
        this.mThumb = in.readParcelable(Image.class.getClassLoader());
    }

    public static final Parcelable.Creator<MovieImages> CREATOR = new Parcelable.Creator<MovieImages>() {
        @Override
        public MovieImages createFromParcel(Parcel source) {
            return new MovieImages(source);
        }

        @Override
        public MovieImages[] newArray(int size) {
            return new MovieImages[size];
        }
    };
}
