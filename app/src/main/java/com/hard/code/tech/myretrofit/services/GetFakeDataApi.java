package com.hard.code.tech.myretrofit.services;

import com.hard.code.tech.myretrofit.models.Albums;
import com.hard.code.tech.myretrofit.models.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetFakeDataApi {

    @GET("photos")
    Call<List<Albums>> getAllAlbums();

    @GET("posts")
    Call<List<Posts>> getAllPosts();

    @POST("posts")
    Call<Posts> newPost(@Body Posts posts);
}
