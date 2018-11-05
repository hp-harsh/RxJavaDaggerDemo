package com.app.myapplicationwithdagger.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.myapplicationwithdagger.annotations.ApiServiceScope;
import com.app.myapplicationwithdagger.annotations.ApplicationQualifier;
import com.app.myapplicationwithdagger.network.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by harsh on 22/3/18.
 */

@Module(includes = NetworkModule.class)
public class ApiServiceModule {

    private String BASE_URL = "http://api.themoviedb.org/3/";

    @Provides
    @ApiServiceScope
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        return new Gson();
    }

    @Provides
    @ApiServiceScope
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build();
    }

    @Provides
    @ApiServiceScope
    public ApiService apiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
