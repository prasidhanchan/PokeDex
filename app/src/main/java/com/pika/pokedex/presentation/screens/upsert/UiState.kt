package com.pika.pokedex.presentation.screens.upsert

data class UiState(
    var nameState: String = "",
    var descriptionState: String = "",
    var typeState: String = "",
    var categoryState: String = "",
    var heightState: String = "",
    var weightState: String = "",
    var colorState: String = "0XFF00AFFF",
    var imageState: String? = null,
    var loading: Boolean = false
)
