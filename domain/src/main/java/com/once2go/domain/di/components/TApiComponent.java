package com.once2go.domain.di.components;

import com.once2go.domain.di.modules.TApiModule;
import com.once2go.domain.di.scopes.NetScope;
import com.once2go.model.network.ServerApi;

import dagger.Component;

/**
 * Created by once2go on 01.08.16.
 */
@NetScope
@Component(dependencies = NetComponent.class, modules = TApiModule.class)
public interface TApiComponent {
    ServerApi getServerApi();
}
