package com.hard.code.tech.myretrofit.retrofit_instance;

import com.hard.code.tech.myretrofit.services.MyApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApiRetrofitInstanceClient {
    public static final String BASE_URL = "http://10.0.2.2/MyApi/public/";

    private static MyApiRetrofitInstanceClient instance;
    private Retrofit retrofit;

    private MyApiRetrofitInstanceClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static synchronized MyApiRetrofitInstanceClient getInstance() {
        if (instance == null) {
            instance = new MyApiRetrofitInstanceClient();
        }

        return instance;

    }

    public MyApi getApi() {
        return retrofit.create(MyApi.class);
    }

}
