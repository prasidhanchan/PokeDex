package com.pika.pokedex.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pika.pokedex.data.repositories.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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

            delay(3000L)
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

    /**
     * Function to get a Pokemon by it's name from API
     * @param name Requires name of the Pokemon to be searched
     */
    fun getPokemonByName(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getPokemonByName(name = name)

            withContext(Dispatchers.Main) {
                if (result.data != null && !result.loading!!) {
                    uiState.update {
                        it.copy(pokemonByName = result.data!!)
                    }
                } else {
                    uiState.update {
                        it.copy(error = result.e?.message.toString())
                    }
                }
            }
        }
    }

    fun setSearchValue(value: String) {
        uiState.update { it.copy(searchState = value) }
    }
}