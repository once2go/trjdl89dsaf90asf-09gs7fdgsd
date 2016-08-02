package com.once2go.model.network;

import com.once2go.model.movies.Movie;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by once2go on 01.08.16.
 */
public interface ServerApi {

    @Deprecated
    @GET("movies/popular")
    Observable<List<Movie>> getPopularMovies();

    @GET("movies/popular")
    Observable<List<Movie>> getPopularMovies(@Query("page") int page, @Query("limit") int offset);



}
