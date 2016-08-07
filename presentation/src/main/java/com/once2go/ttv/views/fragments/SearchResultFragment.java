package com.once2go.ttv.views.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.once2go.model.Config;
import com.once2go.model.SearchResultMovie;
import com.once2go.ttv.R;
import com.once2go.ttv.adapters.MovieSearchAdapter;
import com.once2go.ttv.presenters.SearchResultViewPresenter;
import com.once2go.ttv.presenters.di.components.DaggerSearchResultViewComponent;
import com.once2go.ttv.views.SearchResultView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by once2go on 04.08.16.
 */
public class SearchResultFragment extends Fragment implements SearchResultView {


    private static final String ITEM_LIST_BUNDLE_KEY = "ITEM_LIST_BUNDLE_KEY";
    private static final String PAGE_COUNTER_BUNDLE_KEY = "PAGE_COUNTER_BUNDLE_KEY";
    private static final String QUERY_BUNDLE_KEY = "QUERY_BUNDLE_KEY";

    private SearchResultViewPresenter mSearchViewPresenter;

    private String SEARCH_TYPE = "movie";
    private RecyclerView mListView;
    private final ArrayList<SearchResultMovie> mItemsList = new ArrayList<>();
    private MovieSearchAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private String mSearchQuery;
    private boolean mLoadingInProgress;
    private boolean mEndOfTheList;
    private int mPageCounter;
    private static final int LOAD_ITEMS_OFFSET = 3;

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String value = intent.getExtras().getString(Config.SerchBroadcastConfig.INTENT_QUERY_KEY);
            mSearchQuery = value;
            if (!TextUtils.isEmpty(value)) {
                mPageCounter = 1;
                mEndOfTheList = false;
                mItemsList.clear();
                mSearchViewPresenter.onSearch(SEARCH_TYPE, mSearchQuery, mPageCounter);
            }
        }
    };
    private IntentFilter mSearchIntentFilter = new IntentFilter(Config.SerchBroadcastConfig.INTENT_FILTER);


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            ArrayList<SearchResultMovie> savedList = savedInstanceState.getParcelableArrayList(ITEM_LIST_BUNDLE_KEY);
            mPageCounter = savedInstanceState.getInt(PAGE_COUNTER_BUNDLE_KEY);
            mSearchQuery = savedInstanceState.getString(QUERY_BUNDLE_KEY);
            mItemsList.addAll(savedList);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_view_layout, container, false);
        assignViews(view);
        mSearchViewPresenter = DaggerSearchResultViewComponent.create().getPresenter();
        mSearchViewPresenter.setView(this);
        if (!mItemsList.isEmpty()) {
            mAdapter.notifyDataSetChanged();
        }
        return view;
    }

    private void assignViews(View view) {
        mListView = (RecyclerView) view.findViewById(R.id.search_list_view);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mListView.setLayoutManager(mLayoutManager);
        mAdapter = new MovieSearchAdapter(mItemsList);
        mListView.setAdapter(mAdapter);
        mListView.addOnScrollListener(viewScrollListener);
    }


    private RecyclerView.OnScrollListener viewScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (!mEndOfTheList && !mItemsList.isEmpty() && dy > 0 && !mLoadingInProgress) {
                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();
                if ((visibleItemCount + pastVisibleItems) + LOAD_ITEMS_OFFSET >= totalItemCount) {
                    mLoadingInProgress = true;
                    mPageCounter++;
                    mSearchViewPresenter.onSearch(SEARCH_TYPE, mSearchQuery, mPageCounter);
                }
            }
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(ITEM_LIST_BUNDLE_KEY, mItemsList);
        outState.putInt(PAGE_COUNTER_BUNDLE_KEY, mPageCounter);
        outState.putString(QUERY_BUNDLE_KEY, mSearchQuery);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onSearchResultReceived(List<SearchResultMovie> result) {
        if (result != null && !result.isEmpty()) {
            mLoadingInProgress = false;
            mItemsList.addAll(result);
            mAdapter.notifyDataSetChanged();
        } else {
            mEndOfTheList = true;
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

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(mBroadcastReceiver, mSearchIntentFilter);
    }

    @Override
    public void onPause() {
        getActivity().unregisterReceiver(mBroadcastReceiver);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        if (mListView != null) {
            mListView.removeOnScrollListener(viewScrollListener);
        }
        if (mSearchViewPresenter != null) {
            mSearchViewPresenter.destroy();
            mSearchViewPresenter = null;
        }
        super.onDestroy();
    }
}
