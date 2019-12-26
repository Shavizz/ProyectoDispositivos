package com.example.PokeGOdex.controller;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeClient {

    public static final String BASE_URL ="https://pokeapi.co/api/v2/";

    public static Retrofit getRetrofit(){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());
        return builder.build();
    }
}
