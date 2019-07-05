package com.hard.code.tech.myretrofit.services;

import com.hard.code.tech.myretrofit.models.CountryInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetCountryDataService {

    @GET("country/get/all")
    Call<CountryInfo> getCountries();
}
