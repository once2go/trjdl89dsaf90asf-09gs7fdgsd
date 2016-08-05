package com.once2go.ttv.views.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.once2go.model.Config;
import com.once2go.model.SearchResultMovie;
import com.once2go.ttv.R;
import com.once2go.ttv.presenters.SearchResultViewPresenter;
import com.once2go.ttv.presenters.di.components.DaggerSearchResultViewComponent;
import com.once2go.ttv.views.SearchResultView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by once2go on 04.08.16.
 */
public class SearchResultFragment extends Fragment implements SearchResultView {

    private SearchResultViewPresenter mSearchViewPresenter;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String value = intent.getExtras().getString(Config.SerchBroadcastConfig.INTENT_QUERY_KEY);
            mSearchViewPresenter.onSearch("movie", value);
        }
    };
    private IntentFilter mSearchIntentFilter = new IntentFilter(Config.SerchBroadcastConfig.INTENT_FILTER);

    private TextView mTestTextView;

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
        mTestTextView = (TextView) view.findViewById(R.id.test_tv);
    }

    @Override
    public void onSearchResultReceived(List<SearchResultMovie> result) {
        StringBuilder builder = new StringBuilder();
        for (SearchResultMovie movie : result) {
            builder.append(movie.getMovie().getTitle());
            builder.append("\n");
        }
        mTestTextView.setText(builder.toString());
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
}
