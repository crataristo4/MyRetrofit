package com.hard.code.tech.myretrofit.retrofit_instance;

import com.hard.code.tech.myretrofit.services.GetFakeDataApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FakeDataRetrofitInstance {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    public static Retrofit retrofit = null;

    public static GetFakeDataApi getFakeData() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(GetFakeDataApi.class);

    }
}
