package com.example.pokeapiudev

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApiService {
    @GET("pokemon")
        fun obtenerListaPokemon(): Call<PokemonListResponse>

    @GET("pokemon/{id}")
    fun obtenerPokemon(@Path("id") id: Int): Call<descripcionPokemon>

}


