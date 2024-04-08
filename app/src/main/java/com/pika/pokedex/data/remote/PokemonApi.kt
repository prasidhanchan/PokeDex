package com.pika.pokedex.data.remote

import com.pika.pokedex.BuildConfig.AUTH_KEY
import com.pika.pokedex.domain.models.Content
import com.pika.pokedex.domain.models.Pokemon
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface PokemonApi {
    @GET(value = "get-all-pokemon?size=10&sort=name")
    suspend fun getAllPokemon(): Content

    @POST(value = "add-pokemon?key=${AUTH_KEY}")
    suspend fun addPokemon(@Body pokemon: Pokemon): Response<ResponseBody>
}