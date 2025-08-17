package com.example.ass_restapi.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequest {
    private ApiService service;
    public String BASE_URL = "http://192.168.1.23:3000/";


    public HttpRequest(){
        service = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
    }

    public ApiService callApi(){
        return service;}
}
