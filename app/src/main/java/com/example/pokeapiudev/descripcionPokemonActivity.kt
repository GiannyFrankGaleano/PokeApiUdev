package com.example.pokeapiudev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.util.pool.GlideTrace
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class descripcionPokemonActivity : AppCompatActivity() {
    lateinit var pokeImage: ImageView
    lateinit var name: TextView
    lateinit var height: TextView
    lateinit var weight: TextView
    lateinit var types: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion_pokemon)

        val url = intent.getStringExtra("url")
        pokeImage = findViewById(R.id.imagenPokemon)

        name = findViewById(R.id.name)

        height = findViewById(R.id.height)

        weight = findViewById(R.id.weight)

        types = findViewById(R.id.Types)

        obtenerNumeroUrl(url.toString())
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val PokeApiService = retrofit.create(PokeApiService::class.java)
        PokeApiService.obtenerPokemon(obtenerNumeroUrl(url.toString()))
            .enqueue(object : Callback<descripcionPokemon> {
                override fun onResponse(
                    call: Call<descripcionPokemon>,
                    response: Response<descripcionPokemon>
                ) {
                    if (response.isSuccessful) {
                        runOnUiThread {
                            val pokemon = response.body()
                            if (pokemon != null) {
                                cargarImages(pokemon.sprites.front_default)
                                name.setText(pokemon.name)

                                height.setText(pokemon.height.toString())

                                weight.setText(pokemon.weight.toString())


                            }
                        }
                    }
                }

                override fun onFailure(call: Call<descripcionPokemon>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun obtenerNumeroUrl(url: String): Int {

        val urlSinBarra = url.substringBeforeLast("/")
        val urlNumero = urlSinBarra.substringAfterLast("/")
        return urlNumero.toInt()
    }

    fun cargarImages(url: String) {
        Glide.with(this).load(url).into(pokeImage)
    }


}
