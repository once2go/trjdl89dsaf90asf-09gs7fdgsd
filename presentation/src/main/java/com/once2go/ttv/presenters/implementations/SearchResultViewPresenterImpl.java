package com.once2go.ttv.presenters.implementations;

import android.support.annotation.NonNull;

import com.once2go.domain.interactors.SearchMovieUseCase;
import com.once2go.model.Flag;
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
        search(type, query, Flag.EMPTY_VALUE, Flag.EMPTY_VALUE);
    }

    @Override
    public void onSearch(String type, String query, int page) {
        search(type, query, page, Flag.EMPTY_VALUE);
    }


    @Override
    public void onSearch(String type, String query, int page, int limit) {
        search(type, query, page, limit);
    }

    private void search(String type, String query, int page, int limit) {
        mSearchMovieUseCase = new SearchMovieUseCase(type, query);
        if (limit == Flag.EMPTY_VALUE && page != Flag.EMPTY_VALUE) {
            mSearchMovieUseCase.setQueryCredentials(page);
        }
        if (limit != Flag.EMPTY_VALUE && page != Flag.EMPTY_VALUE) {
            mSearchMovieUseCase.setQueryCredentials(page, limit);
        }
        mSearchResultView.indicateProgress();
        mSearchMovieUseCase.execute(new Subscriber<List<SearchResultMovie>>() {
            @Override
            public void onCompleted() {
                if (mSearchMovieUseCase != null) {
                    mSearchMovieUseCase.unsubscribe();
                    mSearchMovieUseCase = null;
                    mSearchResultView.disableIndication();
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mSearchResultView != null) {
                    mSearchResultView.showError(e.getMessage());
                    mSearchResultView.disableIndication();
                }
            }

            @Override
            public void onNext(List<SearchResultMovie> searchResultMovies) {
                if (mSearchResultView != null) {
                    mSearchResultView.onSearchResultReceived(searchResultMovies);
                    mSearchResultView.disableIndication();
                }
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
        if (mSearchMovieUseCase != null) {
            mSearchMovieUseCase.unsubscribe();
            mSearchMovieUseCase = null;
        }
        mSearchResultView = null;
    }
}
