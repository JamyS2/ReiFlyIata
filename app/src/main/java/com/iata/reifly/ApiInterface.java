package com.iata.reifly;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    // Mock Data
    String loc1 = "DXB";
    String loc2 = "ATQ";
    String loc3 = "SNG";
    String home = "SEA";
    String date = "2019-11-25";

    @GET("/")
    Call<ApiResponse> getOffers();

    @GET("/search")
    Call<ApiResponse> getSearchData(@Query("loc1") String loc1,
                                    @Query("loc2") String loc2,
                                    @Query("loc3") String loc3,
                                    @Query("home") String home,
                                    @Query("date") String date);


}
