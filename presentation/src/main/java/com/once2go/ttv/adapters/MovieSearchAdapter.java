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
public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchViewHolder> {


    private List<SearchResultMovie> mItemsList;

    public MovieSearchAdapter(List<SearchResultMovie> itemsList) {
        if (itemsList == null) {
            throw new IllegalArgumentException("Search result list can't be null");
        }
        mItemsList = itemsList;
    }

    @Override
    public MovieSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_movie_view_item, parent, false);
        return new MovieSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieSearchViewHolder holder, int position) {
        holder.bind(mItemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }
}
