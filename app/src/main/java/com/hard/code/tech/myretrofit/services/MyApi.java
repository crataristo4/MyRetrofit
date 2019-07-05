package com.hard.code.tech.myretrofit.services;

import com.hard.code.tech.myretrofit.models.DefaultResponse;
import com.hard.code.tech.myretrofit.models.LoginResponse;
import com.hard.code.tech.myretrofit.models.UsersResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MyApi {

    //create user
    @FormUrlEncoded
    @POST("createUser")
    Call<DefaultResponse> createUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("school") String school
    );

    //log in
    @FormUrlEncoded
    @POST("userLogin")
    Call<LoginResponse> logInUser(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("allUsers")
    Call<UsersResponse> getAllUsers();

    //update user
    @FormUrlEncoded
    @PUT("updateUser/{id}")
    Call<LoginResponse> updateUser(
            @Path("id") int id,
            @Field("email") String email,
            @Field("name") String name,
            @Field("school") String school

    );

    //update password
    @FormUrlEncoded
    @PUT("updatePassword")
    Call<DefaultResponse> updatePass(
            @Field("email") String email,
            @Field("currentPassword") String currentPassword,
            @Field("newPassword") String newPassword
    );


    @DELETE("deleteUser/{id}")
    Call<DefaultResponse> deleteUser(@Path("id") int id);


}
