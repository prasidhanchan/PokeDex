package com.pika.pokedex.data.remote

import com.pika.pokedex.BuildConfig.AUTH_KEY
import com.pika.pokedex.domain.models.Content
import com.pika.pokedex.domain.models.Pokemon
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface PokemonApi {
    @GET(value = "get-all-pokemon?size=10&sort=name")
    suspend fun getAllPokemon(): Content

    @POST(value = "add-pokemon?key=${AUTH_KEY}")
    suspend fun addPokemon(@Body pokemon: Pokemon): Response<ResponseBody>

    @PATCH(value = "update-pokemon/{id}?key=${AUTH_KEY}")
    suspend fun updatePokemon(@Path("id") id: String, @Body pokemon: Pokemon): Response<ResponseBody>

    @DELETE(value = "remove-pokemon/{id}?key=${AUTH_KEY}")
    suspend fun removePokemon(@Path("id") id: String): Response<ResponseBody>
}