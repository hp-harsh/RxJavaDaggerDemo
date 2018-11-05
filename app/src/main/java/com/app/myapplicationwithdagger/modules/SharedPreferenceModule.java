package com.app.myapplicationwithdagger.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.app.myapplicationwithdagger.annotations.ApiServiceScope;
import com.app.myapplicationwithdagger.annotations.ApplicationQualifier;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by harsh on 22/3/18.
 */

@Module(includes = ContextModule.class)
public class SharedPreferenceModule {

    @Provides
    @ApiServiceScope
    public SharedPreferences sharedPreferences(@ApplicationQualifier Context context) {
        return context.getSharedPreferences("demo-prefs", Context.MODE_PRIVATE);
    }
}
