package com.once2go.ttv.views.activities;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.once2go.ttv.R;
import com.once2go.ttv.views.fragments.PopularMovieFragment;
import com.once2go.ttv.views.fragments.SearchResultFragment;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private PopularMovieFragment mPopularMovieFragment;
    private SearchResultFragment mSearchResultFragment;
    private boolean mIsSearchState;

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
                onBackPressed();
                return true;
            }

            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
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
        return false;
    }

}
