package com.once2go.ttv.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.once2go.model.movies.Movie;
import com.once2go.ttv.R;
import com.once2go.ttv.views.PopularMovieView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by once2go on 02.08.16.
 */
public class PopularMovieFragment extends Fragment implements PopularMovieView {
    private RecyclerView mListView;
    private List<Movie> mItemsList = new ArrayList<>();
    private Movie mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pupular_movie_fragment_layout, container, false);
        return view;
    }

    @Override
    public void onMovieListLoaded(List<Movie> movieList) {

    }

    @Override
    public void indicateProgress() {

    }

    @Override
    public void disableIndication() {

    }

    @Override
    public void showError(String message) {

    }
}
