package com.once2go.model.movies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by once2go on 01.08.16.
 */
public class Movie implements Parcelable {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("year")
    private int mYear;

    @SerializedName("ids")
    private Ids mIds;


    public String getTitle() {
        return mTitle;
    }

    public int getYear() {
        return mYear;
    }

    public Ids getIds() {
        return mIds;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeInt(this.mYear);
        dest.writeParcelable(this.mIds, flags);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.mTitle = in.readString();
        this.mYear = in.readInt();
        this.mIds = in.readParcelable(Ids.class.getClassLoader());
    }

}
