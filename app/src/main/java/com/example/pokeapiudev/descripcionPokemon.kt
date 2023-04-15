package com.example.pokeapiudev

data class descripcionPokemon(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val height: Double,
    val base_experience: Int,
    val types: Types
)
