package com.once2go.ttv.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.once2go.model.SearchResultMovie;
import com.once2go.ttv.R;
import com.squareup.picasso.Picasso;

/**
 * Created by once2go on 05.08.16.
 */
public class MovieSearchViewHolder extends RecyclerView.ViewHolder {

    private TextView mTitle;
    private TextView mYear;
    private TextView mOverview;
    private ImageView mImagePreview;
    private Context mContext;

    public MovieSearchViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        mTitle = (TextView) itemView.findViewById(R.id.search_movie_title);
        mYear = (TextView) itemView.findViewById(R.id.search_movie_year);
        mOverview = (TextView) itemView.findViewById(R.id.search_movie_overview);
        mImagePreview = (ImageView) itemView.findViewById(R.id.search_movie_img_preview);
    }


    public void bind(SearchResultMovie searchResultMovie) {
        int year = searchResultMovie.getMovie().getYear();
        String posterThumb = searchResultMovie.getMovie().getImages().getPoster().getThumb();
        String overview = searchResultMovie.getMovie().getOverview();
        mTitle.setText(searchResultMovie.getMovie().getTitle());
        mYear.setText(year == 0 ? mContext.getString(R.string.empty_year_field) : String.valueOf(year));
        mOverview.setText(TextUtils.isEmpty(overview) ? mContext.getString(R.string.empty_overview_field) : overview);
        Picasso.with(mContext)
                .load(posterThumb)
                .error(R.drawable.ic_cloud_off)
                .placeholder(posterThumb == null ? R.drawable.ic_cloud_off : R.drawable.ic_cloud_download)
                .into(mImagePreview);
    }
}
