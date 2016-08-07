package com.once2go.ttv.views.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.once2go.model.movies.ReachMovie;
import com.once2go.ttv.R;
import com.squareup.picasso.Picasso;

/**
 * Created by denyskarpov on 07.08.16.
 */

public class DetailsActivity extends AppCompatActivity
        implements AppBarLayout.OnOffsetChangedListener, View.OnClickListener {

    private static final int PERCENTAGE_TO_SHOW_IMAGE = 20;
    private static final String REACH_MOVIE_EXTRAS = "REACH_MOVIE_EXTRAS";

    private ReachMovie mMovie;
    private View mFab;
    private int mMaxScrollSize;
    private boolean mIsImageHidden;

    private CollapsingToolbarLayout mColapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_layout);
        mMovie = getIntent().getExtras().getParcelable(REACH_MOVIE_EXTRAS);
        assignView();
    }

    private void assignView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        AppBarLayout appbar = (AppBarLayout) findViewById(R.id.detail_activity_appbar);
        appbar.addOnOffsetChangedListener(this);

        mFab = findViewById(R.id.detail_activity_fab);
        mFab.setOnClickListener(this);
        mColapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.detail_activity_collapsing_toolbar);
        mColapsingToolbar.setTitle(mMovie.getTitle());

        TextView header = (TextView) findViewById(R.id.detail_activity_header);
        header.setText(mMovie.getTagLine());
        TextView overview = (TextView) findViewById(R.id.detail_activity_overview);
        overview.setText(mMovie.getOverview());
        ImageView image = (ImageView) findViewById(R.id.detail_activity_thumb);
        Picasso.with(this).load(mMovie.getImages().getPoster().getMedium()).into(image);

        TextView webSite = (TextView) findViewById(R.id.detail_activity_website);
        webSite.setText(mMovie.getHomePage());
        Linkify.addLinks(webSite, Linkify.WEB_URLS);

        TextView genres = (TextView) findViewById(R.id.detail_activity_genres);
        StringBuilder bilder = new StringBuilder();
        for (String genre : mMovie.getGenres()) {
            bilder.append(genre);
            bilder.append("\n");
        }
        genres.setText(bilder);

        TextView certification = (TextView) findViewById(R.id.detail_activity_certification);
        certification.setText(mMovie.getCertification());
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (mMaxScrollSize == 0) {
            mMaxScrollSize = appBarLayout.getTotalScrollRange();
        }
        int currentScrollPercentage = (Math.abs(i)) * 100 / mMaxScrollSize;
        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_IMAGE) {
            if (!mIsImageHidden) {
                mIsImageHidden = true;
                ViewCompat.animate(mFab).scaleY(0).scaleX(0).start();
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_IMAGE) {
            if (mIsImageHidden) {
                mIsImageHidden = false;
                ViewCompat.animate(mFab).scaleY(1).scaleX(1).start();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_activity_fab:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mMovie.getTrailer()));
                startActivity(intent);
                break;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    public static Intent createIntent(Context context, ReachMovie reachMovie) {
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(REACH_MOVIE_EXTRAS, reachMovie);
        return intent;
    }
}
