package com.once2go.model.network;

import com.once2go.model.SearchResultMovie;
import com.once2go.model.movies.Movie;
import com.once2go.model.movies.ReachMovie;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
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

    @GET("movies/popular")
    Observable<List<ReachMovie>> getPopularMovies(@Query("extended") String extended, @Query("page") int page, @Query("limit") int offset);

    @GET("search/{type}")
    Observable<List<SearchResultMovie>> searchMovie(@Path("type") String type, @Query("query") String query);

    @GET("search/{type}")
    Observable<List<SearchResultMovie>> searchMovie(@Path("type") String type, @Query("query") String query, @Query("extended") String extended);

    @GET("search/{type}")
    Observable<List<SearchResultMovie>> searchMovie(@Path("type") String type, @Query("query") String query, @Query("page") int page, @Query("limit") int offset);

    @GET("search/{type}")
    Observable<List<SearchResultMovie>> searchMovie(@Path("type") String type, @Query("query") String query, @Query("extended") String extended, @Query("page") int page, @Query("limit") int offset);

}
