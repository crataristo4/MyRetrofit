package com.hard.code.tech.myretrofit.services;

import com.hard.code.tech.myretrofit.models.Hero;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    String BASE_URL = "https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<Hero>> getAllMarvel();
}
