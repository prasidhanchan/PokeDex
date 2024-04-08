package com.pika.pokedex.domain.models

/**
 * Wrapper class for receiving Data or Exception
 * @param data The actual data received
 * @param loading Indicator if the data is being loaded or not
 * @param e The Exception thrown if the data is not received
 */
data class DataOrException<T, Boolean, E: Exception>(
    var data: T? = null,
    var loading: Boolean? = null,
    var e: E? = null
)