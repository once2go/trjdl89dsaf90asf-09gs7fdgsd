package com.once2go.model.movies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by once2go on 01.08.16.
 */
public class Ids implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mTrakt);
        dest.writeString(this.mSlug);
        dest.writeString(this.mImdb);
        dest.writeInt(this.mTmdb);
    }

    public Ids() {
    }

    protected Ids(Parcel in) {
        this.mTrakt = in.readInt();
        this.mSlug = in.readString();
        this.mImdb = in.readString();
        this.mTmdb = in.readInt();
    }

    public static final Parcelable.Creator<Ids> CREATOR = new Parcelable.Creator<Ids>() {
        @Override
        public Ids createFromParcel(Parcel source) {
            return new Ids(source);
        }

        @Override
        public Ids[] newArray(int size) {
            return new Ids[size];
        }
    };
}
