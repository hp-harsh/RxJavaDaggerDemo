package com.app.myapplicationwithdagger.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.app.myapplicationwithdagger.annotations.ApiServiceScope;
import com.app.myapplicationwithdagger.model.MoviesResponse;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by harsh on 2/4/18.
 */

@ApiServiceScope
public class NetworkService {

    private static String TAG = NetworkService.class.getSimpleName();
    private final ApiService apiService;

    private ProgressDialog progressDialog;

    @Inject
    public NetworkService(ApiService apiService) {
        this.apiService = apiService;
    }

    public void getTopRatedMovies(Context context, String apiKey, boolean isSilentProgress, GetNetworkCallback callback) {

        if (!isSilentProgress) {
            initProgressDialog(context);
        }

        apiService.getTopRatedMovies("" + apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Function<Throwable, ObservableSource<? extends MoviesResponse>>() {
                    @Override
                    public ObservableSource<? extends MoviesResponse> apply(Throwable throwable) throws Exception {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(getObserver(callback, isSilentProgress));
    }

    private Observer<Object> getObserver(final GetNetworkCallback callback, final boolean isSilentProgress) {
        return new Observer<Object>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Object moviesResponse) {
                callback.onSuccess(moviesResponse);
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(new NetworkError(e));
                closeProgressDialog(isSilentProgress);
            }

            @Override
            public void onComplete() {
                closeProgressDialog(isSilentProgress);
            }
        };
    }

    private void initProgressDialog(Context context) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Log.i(TAG,"initProgressDialog");
    }

    private void closeProgressDialog(boolean isSilentProgress) {
        if (progressDialog != null && !isSilentProgress) {
            progressDialog.dismiss();
            Log.i(TAG,"closeProgressDialog");
        }
    }
}
