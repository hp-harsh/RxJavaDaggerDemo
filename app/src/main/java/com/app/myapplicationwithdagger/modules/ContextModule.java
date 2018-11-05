package com.app.myapplicationwithdagger.modules;

import android.content.Context;

import com.app.myapplicationwithdagger.annotations.ApiServiceScope;
import com.app.myapplicationwithdagger.annotations.ApplicationQualifier;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by harsh on 22/3/18.
 */

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    @ApiServiceScope
    @ApplicationQualifier
    public Context context() {
        return context;
    }
}
