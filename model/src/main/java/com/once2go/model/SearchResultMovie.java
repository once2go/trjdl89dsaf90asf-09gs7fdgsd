package com.once2go.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.once2go.model.movies.ReachMovie;

/**
 * Created by once2go on 04.08.16.
 */
public class SearchResultMovie implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mType);
        dest.writeDouble(this.mScore);
        dest.writeParcelable(this.mMovie, flags);
    }

    public SearchResultMovie() {
    }

    protected SearchResultMovie(Parcel in) {
        this.mType = in.readString();
        this.mScore = in.readDouble();
        this.mMovie = in.readParcelable(ReachMovie.class.getClassLoader());
    }

    public static final Parcelable.Creator<SearchResultMovie> CREATOR = new Parcelable.Creator<SearchResultMovie>() {
        @Override
        public SearchResultMovie createFromParcel(Parcel source) {
            return new SearchResultMovie(source);
        }

        @Override
        public SearchResultMovie[] newArray(int size) {
            return new SearchResultMovie[size];
        }
    };
}
