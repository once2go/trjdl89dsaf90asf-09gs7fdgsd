package com.once2go.ttv.presenters;

import com.once2go.ttv.views.PopularMovieView;

/**
 * Created by once2go on 02.08.16.
 */
public interface PopularMovieVIewPresenter extends Presenter<PopularMovieView> {
    void onLoadPopularMovie();
}
