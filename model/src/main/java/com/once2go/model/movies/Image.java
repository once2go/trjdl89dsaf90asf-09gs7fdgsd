package com.once2go.model.movies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by once2go on 03.08.16.
 */
public class Image implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mFull);
        dest.writeString(this.mMedium);
        dest.writeString(this.mThumb);
    }

    public Image() {
    }

    protected Image(Parcel in) {
        this.mFull = in.readString();
        this.mMedium = in.readString();
        this.mThumb = in.readString();
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
