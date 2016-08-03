package com.once2go.domain.interactors;

import com.once2go.model.ObjectExtension;
import com.once2go.model.movies.ReachMovie;

import java.util.List;

import rx.Observable;

/**
 * Created by once2go on 03.08.16.
 */
public class GetPopularMovieWithExtensionUseCase extends PaginatedUseCase<List<ReachMovie>> {

    private ObjectExtension mObjectExtension;

    public GetPopularMovieWithExtensionUseCase(ObjectExtension objectExtension) {
        mObjectExtension = objectExtension;
    }

    @Override
    protected Observable<List<ReachMovie>> buildUseCaseObservable() {
        return serverApi.getPopularMoviesWithExtensions(mObjectExtension.value(), page, limit);
    }
}
