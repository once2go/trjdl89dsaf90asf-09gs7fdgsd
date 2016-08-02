package com.once2go.model.movies;

import com.google.gson.annotations.SerializedName;

/**
 * Created by once2go on 01.08.16.
 */
public class Movie {

    @SerializedName("title")
    private String mTitle;

    @SerializedName("year")
    private int mYear;

    @SerializedName("ids")
    private Ids mIds;

}
