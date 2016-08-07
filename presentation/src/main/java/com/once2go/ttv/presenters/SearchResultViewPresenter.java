package com.once2go.ttv.presenters;

import com.once2go.ttv.views.SearchResultView;

/**
 * Created by once2go on 04.08.16.
 */
public interface SearchResultViewPresenter extends Presenter<SearchResultView> {

    void onSearch(String type, String query);

    void onSearch(String type, String query, int page);

    void onSearch(String type, String query, int page, int limit);

}
