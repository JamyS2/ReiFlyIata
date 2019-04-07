package com.iata.reifly;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/")
    Call<ApiResponse> getOffers();

}
