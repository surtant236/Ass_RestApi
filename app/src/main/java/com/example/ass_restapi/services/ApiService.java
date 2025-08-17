package com.example.ass_restapi.services;

import com.example.ass_restapi.models.Fruit;

import java.util.ArrayList;

import retrofit2.Call;
import com.example.ass_restapi.models.Response;   // ✅ import model Response của bạn
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("fruits/list")
    Call<Response<ArrayList<Fruit>>> getListFruits();

    @DELETE("fruits/delete/{id}")
    Call<Response<Void>> deleteFruits(@Path("id") String id);

    @POST("fruits/add")
    Call<Response<Fruit>> addFruit(@Body Fruit fruit);

    @PUT("fruits/update/{id}")
    Call<Response<Fruit>> updateFruit(@Path("id") String id, @Body Fruit fruit);
}