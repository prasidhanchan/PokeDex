package com.pika.pokedex.presentation.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.storage.FirebaseStorage
import com.pika.pokedex.data.repositories.Repository
import com.pika.pokedex.domain.util.SuccessOrError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    /**
     * Function to delete Pokemon from API
     * @param id Requires id of the Pokemon to be deleted
     * @param name Requires name of the Pokemon to be deleted
     * @param onSuccess on Success lambda triggered when Pokemon is deleted successfully
     * @param onError on Error lambda triggered when there is a error while deleting a Pokemon
     */
    fun removePokemon(
        id: String,
        name: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.removePokemon(id = id)

            withContext(Dispatchers.Main) {
                if (result.data == SuccessOrError.SUCCESS && !result.loading!!) {
                    FirebaseStorage.getInstance().reference
                        .child("Pokemon")
                        .child("$name.png")
                        .delete()
                    onSuccess()
                } else {
                    onError(result.e?.message.toString())
                }
            }
        }
    }
}