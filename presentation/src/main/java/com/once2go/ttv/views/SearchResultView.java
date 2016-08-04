package com.once2go.ttv.views;

import com.once2go.model.SearchResultMovie;

import java.util.List;

/**
 * Created by once2go on 04.08.16.
 */
public interface SearchResultView extends LoadDataView {

    void onSearchResultReceived(List<SearchResultMovie> result);
}
