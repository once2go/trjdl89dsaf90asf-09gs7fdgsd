package com.once2go.model.movies;

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
}
