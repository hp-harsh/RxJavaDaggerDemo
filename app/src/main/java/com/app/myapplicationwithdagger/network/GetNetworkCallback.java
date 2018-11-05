package com.app.myapplicationwithdagger.network;

import com.app.myapplicationwithdagger.model.MoviesResponse;

/**
 * Created by harsh on 2/4/18.
 */

public interface GetNetworkCallback {

    void onSuccess(Object networkResponse);

    void onError(NetworkError networkError);
}
