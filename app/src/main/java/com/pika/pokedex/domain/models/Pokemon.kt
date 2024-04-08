package com.pika.pokedex.domain.models

import androidx.compose.runtime.Stable

@Stable
data class Pokemon(
    val _id: String? = null,
    val name: String?,
    val image: String?,
    val description: String?,
    val category: String?,
    val type: String?,
    val color: String?,
    val height: String?,
    val weight: String?
)
