package com.once2go.ttv.presenters.di.components;

import com.once2go.ttv.presenters.SearchResultViewPresenter;
import com.once2go.ttv.presenters.di.modules.SearchResultViewModule;

import dagger.Component;

/**
 * Created by once2go on 02.08.16.
 */
@Component(modules = {SearchResultViewModule.class})
public interface SearchResultViewComponent {
    SearchResultViewPresenter getPresenter();
}
