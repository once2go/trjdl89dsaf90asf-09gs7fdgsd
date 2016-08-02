package com.once2go.ttv.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.once2go.model.movies.Movie;
import com.once2go.ttv.R;

/**
 * Created by once2go on 02.08.16.
 */
public class MovieViewHolder extends RecyclerView.ViewHolder {

    private TextView mTitle;
    private TextView mYear;

    public MovieViewHolder(View itemView) {
        super(itemView);
        mTitle = (TextView) itemView.findViewById(R.id.movie_item_title);
        mYear = (TextView) itemView.findViewById(R.id.movie_item_year);
    }

    public void bind(Movie movie) {
        mTitle.setText(movie.getTitle());
        mYear.setText(String.valueOf(movie.getYear()));
    }
}
