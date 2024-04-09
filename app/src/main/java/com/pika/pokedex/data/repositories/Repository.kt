package com.pika.pokedex.data.repositories

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.pika.pokedex.data.remote.PokemonApi
import com.pika.pokedex.domain.models.Content
import com.pika.pokedex.domain.models.DataOrException
import com.pika.pokedex.domain.models.Pokemon
import com.pika.pokedex.domain.util.SuccessOrError
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class Repository @Inject constructor(
    private val pokemonApi: PokemonApi
) {
    suspend fun getAllPokemon(): DataOrException<Content, Boolean, Exception> {
        val dataOrException: DataOrException<Content, Boolean, Exception> = DataOrException()

        try {
            dataOrException.loading = true
            dataOrException.data = pokemonApi.getAllPokemon()
            dataOrException.loading = false
        } catch (e: Exception) {
            dataOrException.e = e
            dataOrException.loading = false
        }
        return dataOrException
    }

    suspend fun addPokemon(pokemon: Pokemon): DataOrException<SuccessOrError, Boolean, Exception> {
        val dataOrException: DataOrException<SuccessOrError, Boolean, Exception> =
            DataOrException(data = SuccessOrError.LOADING)

        try {
            dataOrException.loading = true
            val response = pokemonApi.addPokemon(pokemon = pokemon)
            if (response.isSuccessful) dataOrException.data = SuccessOrError.SUCCESS
            dataOrException.loading = false
        } catch (e: Exception) {
            dataOrException.e = e
            dataOrException.loading = false
            dataOrException.data = SuccessOrError.ERROR
        }
        return dataOrException
    }

    suspend fun updatePokemon(
        id: String,
        pokemon: Pokemon
    ): DataOrException<SuccessOrError, Boolean, Exception> {
        val dataOrException: DataOrException<SuccessOrError, Boolean, Exception> =
            DataOrException(data = SuccessOrError.LOADING)

        try {
            dataOrException.loading = true
            val response = pokemonApi.updatePokemon(
                id = id,
                pokemon = pokemon
            )
            if (response.isSuccessful) {
                dataOrException.data = SuccessOrError.SUCCESS
                dataOrException.loading = false
            }
            Log.d("UPDATTE", "updatePokemon: ${response.code()}")
        } catch (e: Exception) {
            dataOrException.e = e
            dataOrException.data = SuccessOrError.ERROR
            dataOrException.loading = false
        }
        return dataOrException
    }

    suspend fun removePokemon(id: String): DataOrException<SuccessOrError, Boolean, Exception> {
        val dataOrException: DataOrException<SuccessOrError, Boolean, Exception> =
            DataOrException(data = SuccessOrError.LOADING)

        try {
            dataOrException.loading = true
            val response = pokemonApi.removePokemon(id = id)
            if (response.isSuccessful) {
                dataOrException.data = SuccessOrError.SUCCESS
                dataOrException.loading = false
            }
        } catch (e: Exception) {
            dataOrException.e = e
            dataOrException.data = SuccessOrError.ERROR
            dataOrException.loading = false
        }
        return dataOrException
    }

    suspend fun convertToUrl(uri: Uri, name: String): DataOrException<String, Boolean, Exception> {
        val dataOrException: DataOrException<String, Boolean, Exception> = DataOrException()

        try {
            val storageRef =
                FirebaseStorage.getInstance().reference.child("Pokemon").child("$name.png")
            storageRef.putFile(uri)
                .addOnSuccessListener {
                    storageRef.downloadUrl
                        .addOnSuccessListener { url ->
                            dataOrException.data = url.toString()
                        }
                        .addOnFailureListener { error ->
                            dataOrException.e = error
                        }
                }
                .addOnFailureListener { error ->
                    dataOrException.e = error
                }
                .await()
        } catch (e: Exception) {
            dataOrException.e = e
        }
        return dataOrException
    }
}