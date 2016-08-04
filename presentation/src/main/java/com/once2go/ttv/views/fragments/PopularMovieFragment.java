package com.once2go.ttv.views.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.once2go.model.movies.Movie;
import com.once2go.model.movies.ReachMovie;
import com.once2go.ttv.R;
import com.once2go.ttv.adapters.MovieAdapter;
import com.once2go.ttv.presenters.PopularMovieViewPresenter;
import com.once2go.ttv.presenters.di.components.DaggerPopularMovieComponent;
import com.once2go.ttv.views.PopularMovieView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by once2go on 02.08.16.
 */
public class PopularMovieFragment extends Fragment implements PopularMovieView {

    private static final String ITEM_LIST_BUNDLE_KEY = "ITEM_LIST_BUNDLE_KEY";
    private static final String PAGE_COUNTER_BUNDLE_KEY = "PAGE_COUNTER_BUNDLE_KEY";

    private static final int ITEMS_IN_ROW_PORTRET = 3;
    private static final int ITEMS_IN_ROW_LANDSCAPE = 5;
    private PopularMovieViewPresenter mPopularMovieViewPresenter;
    private RecyclerView mListView;
    private ArrayList<ReachMovie> mItemsList = new ArrayList<>();
    private MovieAdapter mAdapter;
    private boolean mLoadingInProgress;
    private boolean mEndOfTheList;
    private int mPageCounter;
    private static final int LOAD_ITEMS_OFFSET = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pupular_movie_fragment_layout, container, false);
        assignViews(view);
        mPopularMovieViewPresenter = DaggerPopularMovieComponent.create().getPresenter();
        mPopularMovieViewPresenter.setView(this);
        if (savedInstanceState != null) {
            ArrayList<ReachMovie> savedList = savedInstanceState.getParcelableArrayList(ITEM_LIST_BUNDLE_KEY);
            mPageCounter = savedInstanceState.getInt(PAGE_COUNTER_BUNDLE_KEY);
            mItemsList.clear();
            mItemsList.addAll(savedList);
            mAdapter.notifyDataSetChanged();
        } else {
            mPageCounter++;
            mPopularMovieViewPresenter.onLoadPopularMovie(mPageCounter);
        }
        return view;
    }


    private void assignViews(View view) {
        mListView = (RecyclerView) view.findViewById(R.id.popular_movie_list_view);
        int itemsInRow;
        int display_mode = getResources().getConfiguration().orientation;
        if (display_mode == Configuration.ORIENTATION_PORTRAIT) {
            itemsInRow = ITEMS_IN_ROW_PORTRET;
        } else {
            itemsInRow = ITEMS_IN_ROW_LANDSCAPE;
        }
        final GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), itemsInRow, LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(layoutManager);
        mAdapter = new MovieAdapter(mItemsList);
        mListView.setAdapter(mAdapter);
        mListView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!mEndOfTheList && !mItemsList.isEmpty() && dy > 0 && !mLoadingInProgress) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                    if ((visibleItemCount + pastVisibleItems) + LOAD_ITEMS_OFFSET >= totalItemCount) {
                        mLoadingInProgress = true;
                        mPageCounter++;
                        mPopularMovieViewPresenter.onLoadPopularMovie(mPageCounter);
                    }
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(ITEM_LIST_BUNDLE_KEY, mItemsList);
        outState.putInt(PAGE_COUNTER_BUNDLE_KEY, mPageCounter);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onMovieListLoaded(List<Movie> movieList) {

    }

    @Override
    public void onReachMovieListLoaded(List<ReachMovie> movieList) {
        mLoadingInProgress = false;
        mItemsList.addAll(movieList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void indicateProgress() {

    }

    @Override
    public void disableIndication() {

    }

    @Override
    public void showError(String message) {
        View v = getView();
        if (v != null) {
            Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy() {
        if (mPopularMovieViewPresenter != null) {
            mPopularMovieViewPresenter.destroy();
            mPopularMovieViewPresenter = null;
        }
        super.onDestroy();
    }
}
