package com.once2go.ttv.presenters.di.modules;

import com.once2go.ttv.presenters.PopularMovieViewPresenter;
import com.once2go.ttv.presenters.implementations.PopularMovieViewPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by once2go on 02.08.16.
 */
@Module
public class PopularMovieModule {

    @Provides
    public PopularMovieViewPresenter providesPopularMovieViewPresenter() {
        return new PopularMovieViewPresenterImpl();
    }

}
