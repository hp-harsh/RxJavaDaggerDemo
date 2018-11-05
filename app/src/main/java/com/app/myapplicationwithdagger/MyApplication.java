package com.app.myapplicationwithdagger;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.app.myapplicationwithdagger.components.DaggerMyApplicationComponent;
import com.app.myapplicationwithdagger.components.MyApplicationComponent;
import com.app.myapplicationwithdagger.modules.ContextModule;
import com.app.myapplicationwithdagger.network.ApiService;
import com.squareup.picasso.Picasso;

import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * Created by harsh on 22/3/18.
 */

public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getSimpleName();

    public static MyApplication get(Activity activity) {
        return (MyApplication) activity.getApplication();
    }

    private String BASE_URL = "http://api.themoviedb.org/3/";

    MyApplicationComponent component;

    // Activity

    // apiService       picasso

    // retrofit         OkHttp3Downloader

    // gson     okhttp

    //          logging_interceptor      catch

    //          timber                   file

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        component = DaggerMyApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    public MyApplicationComponent getApplicationComponent() {
        return component;
    }

    /*public ApiService getApiService() {
        return apiService;
    }

    public Picasso getPicasso() {
        return picasso;
    }*/



    // Phase - 1 Basic structure
    /*
    * @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        // CONTEXT
        Context context = this;

        // NETWORK
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Timber.i(message);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        File cacheFile = new File(getCacheDir(), "okhttp_cache");
        cacheFile.mkdirs();

        Cache cache = new Cache(cacheFile, 10 * 1000 * 1000);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .cache(cache)
                .build();

        // PICASSO
        picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .build();

        // CLIENT
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        Gson gson = new Gson();

        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .baseUrl(BASE_URL)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }

    public Picasso getPicasso() {
        return picasso;
    }
    * */


    // Phase - 2 Why we have need scope
    /*
    * @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        // Don't use @Singleton because it is a java basic Singleton and it use throught out the app.
        // We provide here a custom @ApiServiceScope because we use it for only single component
        MyApplicationComponent component = DaggerMyApplicationComponent.builder()
                .contextModule(new ContextModule(this))
                .build();

        apiService = component.getApiService();
        picasso = component.getPicasso();

        ApiService apiService2 = component.getApiService();
        Picasso picasso2 = component.getPicasso();

        ApiService apiService3 = component.getApiService();
        Picasso picasso3 = component.getPicasso();

        Log.i(TAG, "apiService: " + apiService);
        Log.i(TAG, "picasso: " + picasso);

        Log.i(TAG, "apiService2: " + apiService2);
        Log.i(TAG, "picasso2: " + picasso2);

        Log.i(TAG, "apiService3: " + apiService3);
        Log.i(TAG, "picasso3: " + picasso3);
    }
    * */
}
