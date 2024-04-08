package com.pika.pokedex.presentation.screens.home

import com.pika.pokedex.domain.models.Pokemon

data class UiState(
    var pokemonList: List<Pokemon> = emptyList(),
    var loading: Boolean = false,
    var error: String = ""
)
