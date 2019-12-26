package com.example.PokeGOdex.controller;

import com.example.PokeGOdex.models.pokeMoves.Tipo;
import com.example.PokeGOdex.models.pokeSpecies.PokemonSpecies;
import com.example.PokeGOdex.models.pokeStats.Ability;
import com.example.PokeGOdex.models.pokemon.Pokemon;
import com.example.PokeGOdex.models.pokemon.PokemonRespuesta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeapiService {

    @GET("pokemon")
    Call<PokemonRespuesta> obtenerListaPokemon(@Query("limit") int limit, @Query("offset") int offset);

    @GET("pokemon/{id}")
    Call<Pokemon> obtenerPokemon(@Path("id") int id);

    @GET("pokemon-species/{id}")
    Call<PokemonSpecies> obtenerPokemonSpecies(@Path("id") int id);

    @GET("/ability/{id}")
    Call<Ability> obtenerHabilidad(@Path("id") int id);

    @GET("/type/{id}")
    Call<Tipo> obtenerTipo(@Path("id") int id);
}
