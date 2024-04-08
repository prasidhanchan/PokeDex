package com.pika.pokedex.di

import com.pika.pokedex.data.remote.PokemonApi
import com.pika.pokedex.data.repositories.Repository
import com.pika.pokedex.domain.util.Constants.BASEURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providePokemonApi(): PokemonApi {
        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PokemonApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(pokemonApi: PokemonApi) = Repository(pokemonApi = pokemonApi)
}