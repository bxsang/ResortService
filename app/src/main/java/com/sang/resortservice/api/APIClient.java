package com.sang.resortservice.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIClient {

    @FormUrlEncoded
    @POST("login.php")
    Call<GetUserResult> login(@Field("username") String username, @Field("password") String password);

    @GET("room.php")
    Call<GetRoomTypesResult> getRoomTypes();
}
