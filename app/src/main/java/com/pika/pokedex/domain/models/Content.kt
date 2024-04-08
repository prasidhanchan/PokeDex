package com.pika.pokedex.domain.models

data class Content(
    val content: List<Pokemon>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val totalPages: Int
)