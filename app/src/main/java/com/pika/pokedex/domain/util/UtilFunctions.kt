package com.pika.pokedex.domain.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

/**
 * Parses the Color as HexColor and returns the result as string, ex: 0XFF00AFFF.
 * @return Returns a formatted String.
 */
fun Color.toHexColor(): String {
    val colorInt = this.toArgb()
    return String.format("0X%08X", colorInt)
}
