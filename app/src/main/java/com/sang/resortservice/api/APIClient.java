package com.sang.resortservice.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIClient {

    @FormUrlEncoded
    @POST("login.php")
    Call<GetUserResponse> login(@Field("username") String username, @Field("password") String password);

    @GET("room.php?get_types")
    Call<GetRoomTypesResponse> getRoomTypes();

    @GET("room.php?get_all")
    Call<GetRoomsResponse> getAllRooms();

    @GET("room.php")
    Call<GetRoomsResponse> getRoomsByType(@Query("type") String type);

    @GET("room.php")
    Call<GetRoomIdResponse> getRoomId(@Query("get_room_id") String abc, @Query("room_name") String roomName);

    @FormUrlEncoded
    @POST("booking.php")
    Call<BookingResponse> book(@Field("name") String customerName,
                               @Field("phone_number") String customerPhone,
                               @Field("gender") String customerGender,
                               @Field("email") String customerEmail,
                               @Field("address") String customerAddress,
                               @Field("room_id") int roomId,
                               @Field("checkin_date") String checkinDate,
                               @Field("checkout_date") String checkoutDate);

    @GET("booking.php")
    Call<GetPriceResponse> getPrice(@Query("get_price") String abc, @Query("type_name") String typeName);

    @GET("reservation.php?get_all")
    Call<GetReservationsResponse> getAllReservations();

    @GET("feedback.php?get_all")
    Call<GetFeedbackResponse> getAllFeedbacks();

    @GET("statistics.php")
    Call<GetStatisticsResponse> getStatistics();
}
