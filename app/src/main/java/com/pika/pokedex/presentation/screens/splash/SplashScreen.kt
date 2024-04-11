package com.pika.pokedex.presentation.screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
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

@Composable
fun SplashScreen(navigateToHomeScreen: () -> Unit) {

    val rotation = remember {
        Animatable(
            initialValue = 0f
        )
    }

    LaunchedEffect(key1 = Unit) {
        rotation.animateTo(
            targetValue = 360f,
            animationSpec = tween(durationMillis = 500)
        )
        rotation.animateTo(
            targetValue = 720f,
            animationSpec = tween(
                delayMillis = 200,
                durationMillis = 500
            )
        )

        delay(500L)
        navigateToHomeScreen()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 40.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .weight(6f)
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier.graphicsLayer { rotationZ = rotation.value },
                    painter = painterResource(id = R.drawable.pokedex_icon),
                    contentDescription = stringResource(id = R.string.app_name)
                )
            }
            Text(
                text = stringResource(id = R.string.app_name),
                style = TextStyle(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                    fontFamily = mPlus
                )
            )
        }
    }
}

@Preview
@Composable
private fun SplashScreenPreview() {
    SplashScreen(
        navigateToHomeScreen = { }
    )
}