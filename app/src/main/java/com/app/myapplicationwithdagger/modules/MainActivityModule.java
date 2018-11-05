package com.app.myapplicationwithdagger.modules;

import com.app.myapplicationwithdagger.MainActivity;
import com.app.myapplicationwithdagger.adapters.MoviesAdapter;
import com.app.myapplicationwithdagger.annotations.MainActivityScope;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;

/**
 * Created by harsh on 22/3/18.
 */

@Module
public class MainActivityModule {

    MainActivity mainActivity;

    public MainActivityModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Provides
    @MainActivityScope
    public MainActivity mainActivity() {
        return mainActivity;
    }

    /*@Provides
    @MainActivityScope
    public MoviesAdapter moviesAdapter(Picasso picasso) {
        return new MoviesAdapter(mainActivity, picasso);
    }*/
}
