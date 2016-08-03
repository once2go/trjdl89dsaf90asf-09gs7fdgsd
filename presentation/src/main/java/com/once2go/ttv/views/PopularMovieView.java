package com.once2go.ttv.views;

import com.once2go.model.movies.Movie;
import com.once2go.model.movies.ReachMovie;

import java.util.List;

/**
 * Created by once2go on 02.08.16.
 */
public interface PopularMovieView extends LoadDataView {

    @Deprecated
    void onMovieListLoaded(List<Movie> movieList);

    void onReachMovieListLoaded(List<ReachMovie> movieList);
}
