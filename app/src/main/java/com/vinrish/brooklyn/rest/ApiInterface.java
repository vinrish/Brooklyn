package com.vinrish.brooklyn.rest;

import com.vinrish.brooklyn.model.ApiRequest;
import com.vinrish.brooklyn.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("/login")
    Call<ApiResponse> operation(@Body ApiRequest request);

}
