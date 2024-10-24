package com.pika.pokedex.di

import com.pika.pokedex.data.remote.PokemonApi
import com.pika.pokedex.data.repositories.Repository
import com.pika.pokedex.domain.util.Constants.BASEURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .readTimeout(duration = Duration.ofMinutes(2))
                    .writeTimeout(duration = Duration.ofMinutes(2))
                    .build()
            )
            .build()
            .create(PokemonApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(pokemonApi: PokemonApi) = Repository(pokemonApi = pokemonApi)
}