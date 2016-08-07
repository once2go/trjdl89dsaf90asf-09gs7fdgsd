package com.once2go.domain.di.modules;

import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.once2go.model.Config;

import java.io.IOException;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by once2go on 01.08.16.
 */
@Module
public class NetModule {

    private static final String CLIENT_SECURED = "secured";
    public static final String RETROFIT_SECURED = "secured";
    private static final String LOG_TAG = NetModule.class.getSimpleName();

    private String mClientId;

    public NetModule(String clientId) {
        mClientId = clientId;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        Log.d(LOG_TAG, "provideGson");
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Named(CLIENT_SECURED)
    @Singleton
    OkHttpClient provideSecuredOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("trakt-api-version", String.valueOf(Config.API_VERSION))
                        .addHeader("trakt-api-key", mClientId).build();
                return chain.proceed(request);
            }
        });
        return builder.build();
    }

    @Provides
    @Named(RETROFIT_SECURED)
    @Singleton
    Retrofit provideSecuredRetrofit(Gson gson, @Named(CLIENT_SECURED) OkHttpClient provideSecuredOkHttpClient) {
        Log.d(LOG_TAG, "provideSecuredRetrofit");
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Config.SERVER)
                .client(provideSecuredOkHttpClient)
                .build();
    }
}
