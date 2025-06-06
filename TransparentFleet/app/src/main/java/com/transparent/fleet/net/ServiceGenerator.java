package com.transparent.fleet.net;

import com.squareup.okhttp.OkHttpClient;
import com.transparent.fleet.BaseAppClass;
import com.transparent.fleet.util.Constants;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

public class ServiceGenerator {
    private ServiceGenerator() {
    }

    /**
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createServiceNoAuth(Class<S> serviceClass) {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS);

        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(okHttpClient));

        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                // create Base64 encodet string
                request.addHeader("Accept", "application/json");
                request.addHeader("x-os", "android");
                request.addHeader("x-version", "1");
            }
        });

        RestAdapter adapter = builder.build();


        return adapter.create(serviceClass);

    }

    /**
     * @param serviceClass
     * @param <S>
     * @return
     */
    public static <S> S createService(Class<S> serviceClass) {
        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS);

        // set endpoint url and use OkHTTP as HTTP client
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(Constants.BASE_URL)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(okHttpClient));

            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    // create Base64 encodet string
                    request.addHeader("Accept", "application/json");
                    request.addHeader("Authorization", "Bearer " + BaseAppClass.getPreferences().getToken());
                }
            });

        RestAdapter adapter = builder.build();

        return adapter.create(serviceClass);
    }
}
