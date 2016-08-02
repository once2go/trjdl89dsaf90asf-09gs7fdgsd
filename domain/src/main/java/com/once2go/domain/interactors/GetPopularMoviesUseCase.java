package com.once2go.domain.interactors;

import com.once2go.model.movies.Movie;

import java.util.List;

import rx.Observable;

/**
 * Created by once2go on 02.08.16.
 */
public class GetPopularMoviesUseCase extends PaginatedUseCase<List<Movie>> {

    @Override
    protected Observable<List<Movie>> buildUseCaseObservable() {
        return serverApi.getPopularMovies(page, limit);
    }
}
