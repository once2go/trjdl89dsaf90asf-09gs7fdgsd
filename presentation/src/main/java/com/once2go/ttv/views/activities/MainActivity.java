package com.once2go.ttv.views.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.once2go.model.Config;
import com.once2go.ttv.R;
import com.once2go.ttv.views.fragments.PopularMovieFragment;
import com.once2go.ttv.views.fragments.SearchResultFragment;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String SEARCH_QUERY_BUNDLE_KEY = "SEARCH_QUERY_BUNDLE_KEY";

    private PopularMovieFragment mPopularMovieFragment;
    private SearchResultFragment mSearchResultFragment;
    private boolean mIsSearchState;
    private String mLastSearchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        if (savedInstanceState == null) {
            Log.d(LOG_TAG, "create new fragment");
            mPopularMovieFragment = new PopularMovieFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_activity_content_container, mPopularMovieFragment).addToBackStack(null)
                    .commit();
        } else {
            mLastSearchQuery = savedInstanceState.getString(SEARCH_QUERY_BUNDLE_KEY);
        }
    }

    private void assignViews() {
        Toolbar myToolbar = (Toolbar) findViewById(R.id.main_activity_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(menu.findItem(R.id.search), new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                mIsSearchState = false;
                mLastSearchQuery = "";
                onBackPressed();
                return true;
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }
        });
        if (!TextUtils.isEmpty(mLastSearchQuery)) {
            MenuItemCompat.expandActionView(menu.findItem(R.id.search));
            searchView.setOnQueryTextListener(null);
            searchView.setQuery(mLastSearchQuery, false);
            searchView.setOnQueryTextListener(this);
            mIsSearchState = true;
        }

        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(SEARCH_QUERY_BUNDLE_KEY, mLastSearchQuery);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!mIsSearchState && !newText.isEmpty()) {
            mSearchResultFragment = new SearchResultFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_activity_content_container, mSearchResultFragment)
                    .addToBackStack(null)
                    .commit();
            mIsSearchState = true;
        }
        if (!TextUtils.isEmpty(newText)) {
            mLastSearchQuery = newText;
        }
        Intent intent = new Intent(Config.SerchBroadcastConfig.INTENT_FILTER);
        intent.putExtra(Config.SerchBroadcastConfig.INTENT_QUERY_KEY, newText);
        sendBroadcast(intent);
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
        }
    }
}
