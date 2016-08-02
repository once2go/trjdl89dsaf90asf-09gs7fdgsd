package com.once2go.ttv.presenters.implementations;

import android.support.annotation.NonNull;

import com.once2go.domain.interactors.GetPopularMoviesUseCase;
import com.once2go.model.movies.Movie;
import com.once2go.ttv.presenters.PopularMovieViewPresenter;
import com.once2go.ttv.views.PopularMovieView;

import java.util.List;

import rx.Subscriber;

/**
 * Created by once2go on 02.08.16.
 */
public class PopularMovieViewPresenterImpl implements PopularMovieViewPresenter {

    private PopularMovieView mPopularMovieView;
    private GetPopularMoviesUseCase mGetPopularMoviesUseCase;

    private Subscriber<List<Movie>> mPopularMovieSubscription = new Subscriber<List<Movie>>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            if (mPopularMovieView != null) {
                mPopularMovieView.showError(e.getMessage());
            }
        }

        @Override
        public void onNext(List<Movie> movieList) {
            if (mPopularMovieView != null) {
                mPopularMovieView.onMovieListLoaded(movieList);
            }
        }
    };

    @Override
    public void onLoadPopularMovie() {
        mGetPopularMoviesUseCase = new GetPopularMoviesUseCase();
        mGetPopularMoviesUseCase.execute(mPopularMovieSubscription);
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

    @Override
    public void destroy() {
        if (mGetPopularMoviesUseCase != null) {
            mGetPopularMoviesUseCase.unsubscribe();
            mGetPopularMoviesUseCase = null;
        }
        mPopularMovieView = null;
    }
}
