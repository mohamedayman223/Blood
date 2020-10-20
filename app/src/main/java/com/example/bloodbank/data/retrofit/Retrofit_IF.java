package com.example.bloodbank.data.retrofit;

import com.example.bloodbank.data.model.city.City;
import com.example.bloodbank.data.model.login.Client;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.data.model.posts.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Retrofit_IF {
    @POST("login")
    @FormUrlEncoded
    Call<Login> loginUser(@Field("phone") String phone,
                          @Field("password") String password);


    @GET("blood-types")
    Call<City> getBloodTypes();

    @GET("posts")
    Call<Post> getPosts(@Query("api_token") String phone,
                        @Query("page") int page);


    @POST("post-toggle-favourite")
    @FormUrlEncoded
    Call<Void> addFavPost(@Field("post_id") int postID,
                          @Field("api_token") String token);

    @GET("governorates")
    Call<City> getGovernorate();

    @GET("cities")
    Call<City> getCities(@Query("governorate_id") int governorateID);


    @POST("signup")
    @FormUrlEncoded
    Call<Login> registerClient(
            @Field("name") String name,
            @Field("email") String email,
            @Field("birth_date") String birthDate,
            @Field("city_id") String cityID,
            @Field("phone") String phone,
            @Field("donation_last_date") String lastDate,
            @Field("password") String password,
            @Field("password_confirmation") String confirmPass,
            @Field("blood_type_id") String bloodID
    );

}
