package com.once2go.ttv.views;

import com.once2go.model.movies.Movie;

import java.util.List;

/**
 * Created by once2go on 02.08.16.
 */
public interface PopularMovieView extends LoadDataView {

    void onMovieListLoaded(List<Movie> movieList);

}
