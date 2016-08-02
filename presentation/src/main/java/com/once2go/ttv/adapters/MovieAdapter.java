package com.once2go.ttv.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.once2go.model.movies.Movie;
import com.once2go.ttv.R;

import java.util.List;

/**
 * Created by once2go on 02.08.16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private final List<Movie> mMovieList;

    public MovieAdapter(List<Movie> movieList) {
        if (movieList == null) {
            throw new IllegalArgumentException("List can't be null");
        }
        mMovieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bind(mMovieList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }
}
