package com.pika.pokedex.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pika.pokedex.data.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    var uiState = MutableStateFlow(UiState())
        private set

    init {
        getAllPokemon()
    }

    /**
     * Function to get all Pokemon from API
     */
    fun getAllPokemon() {
        uiState.update { it.copy(loading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getAllPokemon()

            withContext(Dispatchers.Main) {
                if (result.data != null && !result.loading!!) {
                    uiState.update {
                        it.copy(
                            pokemonList = result.data?.content!!,
                            loading = false
                        )
                    }
                } else {
                    uiState.update {
                        it.copy(
                            error = result.e?.message.toString(),
                            loading = false
                        )
                    }
                }
            }
        }
    }
}