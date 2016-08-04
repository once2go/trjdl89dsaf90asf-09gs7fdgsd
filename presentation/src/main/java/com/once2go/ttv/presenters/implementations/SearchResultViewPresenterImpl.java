package com.once2go.ttv.presenters.implementations;

import android.support.annotation.NonNull;

import com.once2go.domain.interactors.SearchMovieUseCase;
import com.once2go.model.SearchResultMovie;
import com.once2go.ttv.presenters.SearchResultViewPresenter;
import com.once2go.ttv.views.SearchResultView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by once2go on 04.08.16.
 */
public class SearchResultViewPresenterImpl implements SearchResultViewPresenter {

    private SearchResultView mSearchResultView;
    private SearchMovieUseCase mSearchMovieUseCase;


    @Override
    public void onSearch(String type, String query) {
        mSearchMovieUseCase = new SearchMovieUseCase(type, query);
        mSearchMovieUseCase.execute(new Subscriber<List<SearchResultMovie>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mSearchResultView.showError(e.getMessage());
            }

            @Override
            public void onNext(List<SearchResultMovie> searchResultMovies) {
                mSearchResultView.onSearchResultReceived(searchResultMovies);
            }
        });
    }

    @Override
    public void setView(@NonNull SearchResultView view) {
        mSearchResultView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
