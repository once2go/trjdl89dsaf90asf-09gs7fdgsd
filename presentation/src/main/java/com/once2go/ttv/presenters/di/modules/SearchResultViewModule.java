package com.once2go.ttv.presenters.di.modules;

import com.once2go.ttv.presenters.SearchResultViewPresenter;
import com.once2go.ttv.presenters.implementations.SearchResultViewPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by once2go on 04.08.16.
 */
@Module
public class SearchResultViewModule {

    @Provides
    public SearchResultViewPresenter providesSearchViewPresenter() {
        return new SearchResultViewPresenterImpl();
    }
}
