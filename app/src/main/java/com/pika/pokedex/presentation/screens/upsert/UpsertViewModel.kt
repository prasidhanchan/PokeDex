package com.pika.pokedex.presentation.screens.upsert

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pika.pokedex.data.repositories.Repository
import com.pika.pokedex.domain.models.Pokemon
import com.pika.pokedex.domain.util.SuccessOrError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class UpsertViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    var uiState = MutableStateFlow(UiState())
        private set

    /**
     * Function to add Pokemon to API
     * @param onSuccess on Success lambda triggered when Pokemon is added successfully
     * @param onError on Error lambda triggered when there is a error while adding a Pokemon
     */
    fun addPokemon(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        uiState.update { it.copy(loading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            val fbResult = repository.convertToUrl(uiState.value.imageState!!.toUri(), uiState.value.nameState)

            delay(800L)
            if (fbResult.data != null) {
                uiState.update { it.copy(imageState = fbResult.data) } // Updating to FB url

                val pokemon = Pokemon(
                    name = uiState.value.nameState.trim(),
                    description = uiState.value.descriptionState.trim(),
                    type = uiState.value.typeState.trim(),
                    category = uiState.value.categoryState.trim(),
                    height = uiState.value.heightState.trim(),
                    weight = uiState.value.weightState.trim(),
                    color = uiState.value.colorState.trim(),
                    image = uiState.value.imageState!!
                )

                val result = repository.addPokemon(pokemon = pokemon)

                withContext(Dispatchers.Main) {
                    if (result.data == SuccessOrError.SUCCESS && !result.loading!!) {
                        onSuccess()
                    } else {
                        onError(result.e?.message.toString())
                    }
                }
            }
        }
    }

    /**
     * Function to update Pokemon available in API
     * @param onSuccess on Success lambda triggered when Pokemon is updated successfully
     * @param onError on Error lambda triggered when there is a error while updating a Pokemon
     */
    fun updatePokemon(
        id: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        uiState.update { it.copy(loading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            if (!uiState.value.imageState?.contains("firebase")!!) {
                val fbResult = repository.convertToUrl(
                    uri = uiState.value.imageState?.toUri()!!,
                    name = uiState.value.nameState
                )

                delay(800L)
                if (fbResult.e == null) uiState.update { it.copy(imageState = fbResult.data) } // Updating to FB url
            }

            val pokemon = Pokemon(
                _id = id,
                name = uiState.value.nameState,
                description = uiState.value.descriptionState,
                type = uiState.value.typeState,
                category = uiState.value.categoryState,
                height = uiState.value.heightState,
                weight = uiState.value.weightState,
                image = uiState.value.imageState,
                color = uiState.value.colorState
            )

            val result = repository.updatePokemon(
                id = id,
                pokemon = pokemon
            )

            withContext(Dispatchers.Main) {
                if (result.data == SuccessOrError.SUCCESS && !result.loading!!) {
                    onSuccess()
                    uiState.update { it.copy(loading = false) }
                } else {
                    onError(result.e?.message.toString())
                    uiState.update {
                        it.copy(loading = false)
                    }
                }
            }
        }
    }

    fun setId(value: String) {
        uiState.update { it.copy(idState = value) }
    }

    fun setName(value: String) {
        uiState.update { it.copy(nameState = value) }
    }

    fun setDescription(value: String) {
        uiState.update { it.copy(descriptionState = value) }
    }

    fun setType(value: String) {
        uiState.update { it.copy(typeState = value) }
    }

    fun setCategory(value: String) {
        uiState.update { it.copy(categoryState = value) }
    }

    fun setHeight(value: String) {
        uiState.update { it.copy(heightState = value) }
    }

    fun setWeight(value: String) {
        uiState.update { it.copy(weightState = value) }
    }

    fun setColor(value: String) {
        uiState.update { it.copy(colorState = value) }
    }

    fun setImage(value: String) {
        uiState.update { it.copy(imageState = value) }
    }
}