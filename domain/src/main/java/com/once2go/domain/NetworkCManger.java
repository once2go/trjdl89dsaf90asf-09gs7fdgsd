package com.once2go.domain;

import com.once2go.domain.di.components.DaggerNetComponent;
import com.once2go.domain.di.components.DaggerTApiComponent;
import com.once2go.domain.di.components.NetComponent;
import com.once2go.domain.di.components.TApiComponent;
import com.once2go.domain.di.modules.NetModule;
import com.once2go.domain.di.modules.TApiModule;

/**
 * Created by once2go on 01.08.16.
 */

public class NetworkCManger {

    private final TApiComponent mTApiComponent;
    private static NetworkCManger sInstance;

    {
        sInstance = this;
    }

    public NetworkCManger(String clientId) {
        NetComponent netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule(clientId))
                .build();

        mTApiComponent = DaggerTApiComponent.builder()
                .netComponent(netComponent)
                .tApiModule(new TApiModule())
                .build();
    }

    public TApiComponent getTApiComponent() {
        return mTApiComponent;
    }

    public static NetworkCManger getInstance() {
        return sInstance;
    }
}
