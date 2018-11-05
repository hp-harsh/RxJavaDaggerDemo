package com.app.myapplicationwithdagger;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.app.myapplicationwithdagger.adapters.MoviesAdapter;
import com.app.myapplicationwithdagger.components.DaggerMainActivityComponent;
import com.app.myapplicationwithdagger.components.MainActivityComponent;
import com.app.myapplicationwithdagger.model.Movie;
import com.app.myapplicationwithdagger.model.MoviesResponse;
import com.app.myapplicationwithdagger.modules.MainActivityModule;
import com.app.myapplicationwithdagger.network.ApiService;
import com.app.myapplicationwithdagger.network.GetNetworkCallback;
import com.app.myapplicationwithdagger.network.NetworkError;
import com.app.myapplicationwithdagger.network.NetworkService;
import com.app.myapplicationwithdagger.util.SharedPrefsHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements GetNetworkCallback {

    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String API_KEY = "4c34c9a4f7b7580084dcc476842f5f33";

    @Inject
    NetworkService networkService;

    @Inject
    Picasso picasso;

    @Inject
    SharedPrefsHelper sharedPrefsHelper;

    @Inject
    MoviesAdapter moviesAdapter;

    private List<Movie> movieList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityComponent component = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .myApplicationComponent(MyApplication.get(this).getApplicationComponent())
                .build();

        component.mainActivityInject(this);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(moviesAdapter);

        sharedPrefsHelper.put("access_token","123456789");

        String accessToken = sharedPrefsHelper.get("access_token","123qwerty");

        networkService.getTopRatedMovies(MainActivity.this, API_KEY, false, this);
    }

    @Override
    public void onSuccess(Object networkResponse) {
        if (networkResponse instanceof  MoviesResponse) {

            MoviesResponse moviewResponse = (MoviesResponse) networkResponse;
            movieList = moviewResponse.getResults();
            Log.i(TAG, "Number of movies received: " + moviewResponse.getResults().size());

            moviesAdapter.setMovieList(movieList);
        }
    }

    @Override
    public void onError(NetworkError networkError) {
        // Log error here since request failed194
        Log.e(TAG, networkError.toString());
    }
}