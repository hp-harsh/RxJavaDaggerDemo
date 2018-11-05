package com.app.myapplicationwithdagger.components;

import com.app.myapplicationwithdagger.MainActivity;
import com.app.myapplicationwithdagger.adapters.MoviesAdapter;
import com.app.myapplicationwithdagger.annotations.MainActivityScope;
import com.app.myapplicationwithdagger.modules.MainActivityModule;
import com.app.myapplicationwithdagger.network.ApiService;
import com.squareup.picasso.Picasso;

import dagger.Component;

/**
 * Created by harsh on 22/3/18.
 */

@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = MyApplicationComponent.class)
public interface MainActivityComponent {

    void mainActivityInject(MainActivity mainActivity);

    /*ApiService apiService();

    MoviesAdapter moviesAdapter();*/
}
