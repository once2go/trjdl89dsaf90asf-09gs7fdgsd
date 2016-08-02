package com.once2go.domain.di.modules;

import com.once2go.domain.di.scopes.NetScope;
import com.once2go.model.network.ServerApi;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by once2go on 01.08.16.
 */

@Module
public class TApiModule {

    @Provides
    @NetScope
    public ServerApi providesServerApi(@Named(NetModule.RETROFIT_SECURED) Retrofit retrofit) {
        return retrofit.create(ServerApi.class);
    }
}
