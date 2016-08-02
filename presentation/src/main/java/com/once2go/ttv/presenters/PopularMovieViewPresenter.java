package com.once2go.ttv.presenters;

import com.once2go.ttv.views.PopularMovieView;

/**
 * Created by once2go on 02.08.16.
 */
public interface PopularMovieViewPresenter extends Presenter<PopularMovieView> {

    /**
     * @deprecated This method is deprecated according to second
     * step of task. Use {@link #onLoadPopularMovie(int page, int limit)}
     */
    @Deprecated
    void onLoadPopularMovie();

    void onLoadPopularMovie(int page);

    void onLoadPopularMovie(int page, int limit);
}
