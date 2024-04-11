package com.pika.pokedex.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.pika.pokedex.R
import com.pika.pokedex.presentation.ui.theme.mPlus
import kotlinx.coroutines.delay

/**
 * Pokemon Not Found Text composable with animated visibility
 */
@Composable
fun PokemonNotFoundComp() {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        delay(800L)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 350,
                delayMillis = 800
            )
        ),
        exit = fadeOut(
            animationSpec = tween(durationMillis = 350)
        )
    ) {
        Text(
            text = stringResource(R.string.pokemon_not_found),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = mPlus
            ),
            textAlign = TextAlign.Center
        )
    }
}