package com.example.spacex.API;

import com.example.spacex.model.crew_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface spacex_api {

    @GET("crew")
    Call<List<crew_model>> getcrewdetails();
}
