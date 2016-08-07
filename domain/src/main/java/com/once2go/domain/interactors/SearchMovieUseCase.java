package com.once2go.domain.interactors;

import android.text.TextUtils;

import com.once2go.model.ObjectExtension;
import com.once2go.model.SearchResultMovie;

import java.util.List;

import rx.Observable;

/**
 * Created by once2go on 04.08.16.
 */
public class SearchMovieUseCase extends PaginatedUseCase<List<SearchResultMovie>> {

    private String mType;
    private String mQuery;

    public SearchMovieUseCase(String type, String query) {
        if (TextUtils.isEmpty(type)) {
            throw new IllegalArgumentException("Type is empty. Please handle case before requesting");
        }
        if (TextUtils.isEmpty(type)) {
            throw new IllegalArgumentException("Query is empty. Please handle case before requesting");
        }
        mType = type;
        mQuery = query;
    }

    @Override
    protected Observable<List<SearchResultMovie>> buildUseCaseObservable() {
        return serverApi.searchMovie(mType, mQuery, ObjectExtension.FULL_INFO.value(), page, limit);
    }
}
