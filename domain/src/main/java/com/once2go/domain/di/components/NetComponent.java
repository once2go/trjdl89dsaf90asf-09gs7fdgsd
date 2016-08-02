package com.once2go.domain.di.components;

import com.once2go.domain.di.modules.NetModule;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by once2go on 01.08.16.
 */
@Singleton
@Component(modules = {NetModule.class})
public interface NetComponent {
    @Named(NetModule.RETROFIT_SECURED)
    Retrofit retrofit();
}
