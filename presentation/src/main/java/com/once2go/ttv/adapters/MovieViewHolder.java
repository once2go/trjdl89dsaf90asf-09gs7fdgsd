package com.once2go.ttv.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.once2go.model.movies.ReachMovie;
import com.once2go.ttv.R;
import com.once2go.ttv.views.activities.DetailsActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by once2go on 02.08.16.
 */
public class MovieViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private TextView mTitle;
    private TextView mYear;
    private TextView mVoteCounter;
    private ImageView mLogoView;
    private View mView;

    public MovieViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mView = itemView;
        mTitle = (TextView) itemView.findViewById(R.id.movie_item_title);
        mYear = (TextView) itemView.findViewById(R.id.movie_item_year);
        mVoteCounter = (TextView) itemView.findViewById(R.id.movie_item_votes);
        mLogoView = (ImageView) itemView.findViewById(R.id.movie_view_logo);
    }

    public void bind(final ReachMovie movie) {
        mTitle.setText(movie.getTitle());
        mYear.setText(String.valueOf(movie.getYear()));
        mVoteCounter.setText(String.valueOf(movie.getVotes()));
        Picasso.with(mContext).load(movie.getImages().getPoster().getThumb()).placeholder(R.drawable.ic_cloud_download).into(mLogoView);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(DetailsActivity.createIntent(mContext, movie));
            }
        });
    }
}
