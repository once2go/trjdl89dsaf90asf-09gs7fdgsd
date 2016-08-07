package com.once2go.ttv.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.once2go.model.movies.ReachMovie;
import com.once2go.ttv.R;

import java.util.List;

/**
 * Created by once2go on 02.08.16.
 */
public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<ReachMovie> mMovieList;

    private final int VIEW_TYPE_ITEM = 1;
    private final int VIEW_TYPE_PROGRESSBAR = 0;
    private boolean mIsFooterEnabled = true;

    public MovieAdapter(List<ReachMovie> movieList) {
        if (movieList == null) {
            throw new IllegalArgumentException("List can't be null");
        }
        mMovieList = movieList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.movie_view_item, parent, false);
            return new MovieViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.progress_layout, parent, false);
            return new ProgressViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mMovieList.size() > 0 && position < mMovieList.size()) {
            ((MovieViewHolder) holder).bind(mMovieList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return (mIsFooterEnabled) ? mMovieList.size() + 1 : mMovieList.size();
    }

    public void indicateLoading(boolean isEnabled) {
        mIsFooterEnabled = isEnabled;
    }

    @Override
    public int getItemViewType(int position) {
        return (mIsFooterEnabled && position >= mMovieList.size()) ? VIEW_TYPE_PROGRESSBAR : VIEW_TYPE_ITEM;
    }
}
