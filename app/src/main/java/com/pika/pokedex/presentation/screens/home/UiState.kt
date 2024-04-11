package com.pika.pokedex.presentation.screens.home

import com.pika.pokedex.domain.models.Pokemon

data class UiState(
    var pokemonList: List<Pokemon> = emptyList(),
    var pokemonByName: List<Pokemon> = listOf(),
    var searchState: String = "",
    var loading: Boolean = false,
    var error: String = ""
)
