package com.example.bloodbank.data.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRetrofit {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://ipda3-tech.com/blood-bank/api/v1/";

    public static Retrofit_IF getInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(Retrofit_IF.class);
    }



}
