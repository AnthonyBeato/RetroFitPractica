package com.example.retrofit.api;

import com.example.retrofit.dto.UserList;
import com.example.retrofit.dto.UserSingle;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIInterface {
    @GET("users")
    Call<UserList> findAll();

    @GET("users/{id}")
    Call<UserSingle  > find(@Path("id") int id);

}
