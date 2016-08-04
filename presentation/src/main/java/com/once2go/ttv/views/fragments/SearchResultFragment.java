package com.once2go.ttv.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.once2go.model.SearchResultMovie;
import com.once2go.ttv.R;
import com.once2go.ttv.presenters.SearchResultViewPresenter;
import com.once2go.ttv.presenters.di.components.DaggerSearchResultViewComponent;
import com.once2go.ttv.views.SearchResultView;

import java.util.List;

/**
 * Created by once2go on 04.08.16.
 */
public class SearchResultFragment extends Fragment implements SearchResultView {

    private SearchResultViewPresenter mSearchViewPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_view_layout, container, false);
        assignViews(view);
        mSearchViewPresenter = DaggerSearchResultViewComponent.create().getPresenter();
        mSearchViewPresenter.setView(this);
        return view;
    }

    private void assignViews(View view) {

    }

    @Override
    public void onStart() {
        super.onStart();
        mSearchViewPresenter.onSearch("movie", "tron");
    }

    @Override
    public void onSearchResultReceived(List<SearchResultMovie> result) {
        for (SearchResultMovie movie : result) {
            Log.d("test", movie.getMovie().getTitle());
        }
    }

    @Override
    public void indicateProgress() {

    }

    @Override
    public void disableIndication() {

    }

    @Override
    public void showError(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }
}
