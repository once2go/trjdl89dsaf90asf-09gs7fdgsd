package com.once2go.ttv.presenters.implementations;

import android.support.annotation.NonNull;

import com.once2go.domain.interactors.GetPopularMovieWithExtensionUseCase;
import com.once2go.model.Flag;
import com.once2go.model.ObjectExtension;
import com.once2go.model.movies.ReachMovie;
import com.once2go.ttv.presenters.PopularMovieViewPresenter;
import com.once2go.ttv.views.PopularMovieView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by once2go on 02.08.16.
 */
public class PopularMovieViewPresenterImpl implements PopularMovieViewPresenter {

    private PopularMovieView mPopularMovieView;
    private GetPopularMovieWithExtensionUseCase mGetPopularMoviesUseCase;

    @Override
    public void onLoadPopularMovie() {
        loadMovieList(Flag.EMPTY_VALUE, Flag.EMPTY_VALUE);
    }

    @Override
    public void onLoadPopularMovie(int page) {
        loadMovieList(page, Flag.EMPTY_VALUE);
    }

    @Override
    public void onLoadPopularMovie(int page, int limit) {
        loadMovieList(page, limit);
    }

    @Override
    public void setView(@NonNull PopularMovieView view) {
        mPopularMovieView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    private void loadMovieList(int page, int limit) {
        mGetPopularMoviesUseCase = new GetPopularMovieWithExtensionUseCase(ObjectExtension.FULL_INFO);
        if (limit == Flag.EMPTY_VALUE && page != Flag.EMPTY_VALUE) {
            mGetPopularMoviesUseCase.setQueryCredentials(page);
        }
        if (limit != Flag.EMPTY_VALUE && page != Flag.EMPTY_VALUE) {
            mGetPopularMoviesUseCase.setQueryCredentials(page, limit);
        }
        mGetPopularMoviesUseCase.execute(new Subscriber<List<ReachMovie>>() {
            @Override
            public void onCompleted() {
                if (mGetPopularMoviesUseCase != null) {
                    mGetPopularMoviesUseCase.unsubscribe();
                    mGetPopularMoviesUseCase = null;
                }
            }

            @Override
            public void onError(Throwable e) {
                if (mPopularMovieView != null) {
                    mPopularMovieView.showError(e.getMessage());
                }
            }

            @Override
            public void onNext(List<ReachMovie> movies) {
                if (mPopularMovieView != null) {
                    mPopularMovieView.onReachMovieListLoaded(movies);
                }
            }
        });
    }

    @Override
    public void destroy() {
        if (mGetPopularMoviesUseCase != null) {
            mGetPopularMoviesUseCase.unsubscribe();
            mGetPopularMoviesUseCase = null;
        }
        mPopularMovieView = null;
    }
}
