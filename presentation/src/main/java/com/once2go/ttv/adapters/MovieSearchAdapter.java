package com.once2go.ttv.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.once2go.model.SearchResultMovie;
import com.once2go.ttv.R;

import java.util.List;

/**
 * Created by once2go on 05.08.16.
 */
public class MovieSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<SearchResultMovie> mItemsList;

    private final int VIEW_TYPE_ITEM = 1;
    private final int VIEW_TYPE_PROGRESSBAR = 0;
    private boolean mIsFooterEnabled = true;

    public MovieSearchAdapter(List<SearchResultMovie> itemsList) {
        if (itemsList == null) {
            throw new IllegalArgumentException("Search result list can't be null");
        }
        mItemsList = itemsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.search_movie_view_item, parent, false);
            return new MovieSearchViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.progress_layout, parent, false);
            return new ProgressViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mItemsList.size() > 0 && position < mItemsList.size()) {
            ((MovieSearchViewHolder) holder).bind(mItemsList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return (mIsFooterEnabled) ? mItemsList.size() + 1 : mItemsList.size();
    }


    public void indicateLoading(boolean isEnabled) {
        mIsFooterEnabled = isEnabled;
    }

    @Override
    public int getItemViewType(int position) {
        return (mIsFooterEnabled && position >= mItemsList.size()) ? VIEW_TYPE_PROGRESSBAR : VIEW_TYPE_ITEM;
    }
}
