package com.app.myapplicationwithdagger.modules;

import android.content.Context;

import com.app.myapplicationwithdagger.annotations.ApiServiceScope;
import com.app.myapplicationwithdagger.annotations.ApplicationQualifier;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by harsh on 22/3/18.
 */

@Module(includes = NetworkModule.class)
public class PicassoModule {


    @Provides
    @ApiServiceScope
    public Picasso picasso(OkHttp3Downloader okHttp3Downloader, @ApplicationQualifier Context context) {
        return new Picasso.Builder(context)
                .downloader(okHttp3Downloader)
                .build();
    }

    @Provides
    @ApiServiceScope
    public OkHttp3Downloader okHttp3Downloader(OkHttpClient okHttpClient) {
        return new OkHttp3Downloader(okHttpClient);
    }
}
