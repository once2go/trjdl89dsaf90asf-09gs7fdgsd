package com.once2go.ttv.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.once2go.ttv.R;
import com.once2go.ttv.views.fragments.PopularMovieFragment;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Log.d(LOG_TAG, "create new fragment");
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_activity_content_container, new PopularMovieFragment())
                    .commit();
        }
    }
}
