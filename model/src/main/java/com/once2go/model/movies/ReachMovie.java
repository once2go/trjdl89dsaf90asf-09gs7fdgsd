package com.once2go.model.movies;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;
import com.once2go.model.Config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by once2go on 03.08.16.
 */
public class ReachMovie extends Movie {

    @SerializedName("tagline")
    private String mTagLine;

    @SerializedName("overview")
    private String mOverview;

    @SerializedName("released")
    private String mReleased;

    @SerializedName("runtime")
    private int mRuntime;

    @SerializedName("trailer")
    private String mTrailer;

    @SerializedName("homepage")
    private String mHomePage;

    @SerializedName("rating")
    private double mRaiting;

    @SerializedName("votes")
    private long mVotes;

    @SerializedName("updated_at")
    private String mUpdatedAt;

    @SerializedName("language")
    private String mLanguage;

    @SerializedName("available_translations")
    private List<String> mAvailableTranslations;

    @SerializedName("genres")
    private List<String> mGenres;

    @SerializedName("certification")
    private String mCertification;

    @SerializedName("images")
    private MovieImages mImages;


    public String getTagLine() {
        return mTagLine;
    }

    public String getOverview() {
        return mOverview;
    }

    public String getReleased() {
        return mReleased;
    }

    public int getRuntime() {
        return mRuntime;
    }

    public String getTrailer() {
        return mTrailer;
    }

    public String getHomePage() {
        return mHomePage;
    }

    public double getRaiting() {
        return mRaiting;
    }

    public long getVotes() {
        return mVotes;
    }

    public Calendar getUpdatedAt() throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat(Config.DATE_FORMAT_PATTERN).parse(mUpdatedAt));
        return cal;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public List<String> getAvailableTranslations() {
        return mAvailableTranslations;
    }

    public List<String> getGenres() {
        return mGenres;
    }

    public String getCertification() {
        return mCertification;
    }

    public MovieImages getImages() {
        return mImages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(this.mTagLine);
        dest.writeString(this.mOverview);
        dest.writeString(this.mReleased);
        dest.writeInt(this.mRuntime);
        dest.writeString(this.mTrailer);
        dest.writeString(this.mHomePage);
        dest.writeDouble(this.mRaiting);
        dest.writeLong(this.mVotes);
        dest.writeString(this.mUpdatedAt);
        dest.writeString(this.mLanguage);
        dest.writeStringList(this.mAvailableTranslations);
        dest.writeStringList(this.mGenres);
        dest.writeString(this.mCertification);
        dest.writeParcelable(this.mImages, flags);
    }

    public ReachMovie() {
    }

    protected ReachMovie(Parcel in) {
        super(in);
        this.mTagLine = in.readString();
        this.mOverview = in.readString();
        this.mReleased = in.readString();
        this.mRuntime = in.readInt();
        this.mTrailer = in.readString();
        this.mHomePage = in.readString();
        this.mRaiting = in.readDouble();
        this.mVotes = in.readLong();
        this.mUpdatedAt = in.readString();
        this.mLanguage = in.readString();
        this.mAvailableTranslations = in.createStringArrayList();
        this.mGenres = in.createStringArrayList();
        this.mCertification = in.readString();
        this.mImages = in.readParcelable(MovieImages.class.getClassLoader());
    }

    public static final Creator<ReachMovie> CREATOR = new Creator<ReachMovie>() {
        @Override
        public ReachMovie createFromParcel(Parcel source) {
            return new ReachMovie(source);
        }

        @Override
        public ReachMovie[] newArray(int size) {
            return new ReachMovie[size];
        }
    };
}
