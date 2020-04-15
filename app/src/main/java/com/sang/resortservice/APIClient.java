package com.sang.resortservice;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIClient {

    @FormUrlEncoded
    @POST("login.php")
    Call<User> login(@Field("username") String username, @Field("password") String password);
}
