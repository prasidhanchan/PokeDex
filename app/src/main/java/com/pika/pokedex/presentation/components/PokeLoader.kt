package com.pika.pokedex.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pika.pokedex.R
import com.pika.pokedex.presentation.ui.theme.mPlus
import kotlinx.coroutines.delay

/**
 * Loader composable for loading indication
 */
@Composable
fun PokeLoader() {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        repeat(Int.MAX_VALUE) {
            delay(800L)
            visible = !visible
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background.copy(alpha = 0.4f)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(Alignment.CenterVertically)
                .animateContentSize(
                    animationSpec = tween(durationMillis = 400)
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                painter = painterResource(id = R.drawable.loader),
                contentDescription = stringResource(R.string.loading)
            )

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(
                    animationSpec = tween(durationMillis = 800)
                ),
                exit = fadeOut(
                    animationSpec = tween(durationMillis = 800)
                )
            ) {
                Text(
                    text = stringResource(R.string.loading_msg),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = mPlus
                    )
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun PokeLoaderPreview() {
    PokeLoader()
}