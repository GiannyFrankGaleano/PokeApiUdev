package com.example.pokeapiudev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var buttonPokemonLayout: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonPokemonLayout = findViewById(R.id.layout_boton_pokemon)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val PokeApiService = retrofit.create(PokeApiService::class.java)

        PokeApiService.obtenerListaPokemon().enqueue(object : Callback<PokemonListResponse> {
            override fun onResponse(
                call: Call<PokemonListResponse>,
                response: Response<PokemonListResponse>
            ) {
                if (response.isSuccessful) {
                    val pokeList = response.body()?.results
                    pokeList?.forEach {
                        crearButton(it)
                    }
                }
            }

            override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun crearButton(pokemon: Pokemon) {
        val button = Button(this)
        button.text = "${pokemon.name}"
        button.setOnClickListener {

            val intent = Intent(this,descripcionPokemonActivity::class.java)

            intent.putExtra("url",pokemon.url)
            startActivity(intent)
        }
        buttonPokemonLayout.addView(button)
    }
}