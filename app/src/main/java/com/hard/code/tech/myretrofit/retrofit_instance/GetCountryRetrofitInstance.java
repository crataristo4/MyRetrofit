package com.hard.code.tech.myretrofit.retrofit_instance;

import com.hard.code.tech.myretrofit.services.GetCountryDataService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetCountryRetrofitInstance {
    private static final String BASE_URL = "http://services.groupkt.com/";
    private static Retrofit retrofit = null;

    public static GetCountryDataService getDataService() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }

        return retrofit.create(GetCountryDataService.class);
    }
}
