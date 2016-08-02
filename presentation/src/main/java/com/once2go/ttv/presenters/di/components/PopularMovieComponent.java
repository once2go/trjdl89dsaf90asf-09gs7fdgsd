package com.once2go.ttv.presenters.di.components;

import com.once2go.ttv.presenters.PopularMovieViewPresenter;
import com.once2go.ttv.presenters.di.modules.PopularMovieModule;

import dagger.Component;

/**
 * Created by once2go on 02.08.16.
 */
@Component(modules = {PopularMovieModule.class})
public interface PopularMovieComponent {
    PopularMovieViewPresenter getPresenter();
}
