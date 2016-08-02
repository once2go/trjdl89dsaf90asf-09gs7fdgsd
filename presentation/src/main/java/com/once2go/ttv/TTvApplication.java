package com.once2go.ttv;

import android.app.Application;

import com.once2go.domain.NetworkCManger;
import com.once2go.model.Config;

/**
 * Created by once2go on 02.08.16.
 */
public class TTvApplication extends Application {


    private static TTvApplication sInstance;

    {
        sInstance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new NetworkCManger(Config.CLIENT_ID);
    }
}
