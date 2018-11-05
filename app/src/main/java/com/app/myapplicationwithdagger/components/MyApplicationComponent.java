package com.app.myapplicationwithdagger.components;

import android.content.SharedPreferences;

import com.app.myapplicationwithdagger.annotations.ApiServiceScope;
import com.app.myapplicationwithdagger.modules.ApiServiceModule;
import com.app.myapplicationwithdagger.modules.PicassoModule;
import com.app.myapplicationwithdagger.modules.SharedPreferenceModule;
import com.app.myapplicationwithdagger.network.ApiService;
import com.app.myapplicationwithdagger.network.NetworkService;
import com.app.myapplicationwithdagger.util.SharedPrefsHelper;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by harsh on 22/3/18.
 */

@ApiServiceScope
@Component(modules = {ApiServiceModule.class, PicassoModule.class, SharedPreferenceModule.class})
public interface MyApplicationComponent {

    Picasso getPicasso();

    ApiService getApiService();

    SharedPreferences getSharedPreferences();

    SharedPrefsHelper getSharedPrefsHelper();

    NetworkService getNetworkService();
}