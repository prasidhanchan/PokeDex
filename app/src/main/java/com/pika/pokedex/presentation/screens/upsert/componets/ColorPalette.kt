package com.pika.pokedex.presentation.screens.upsert.componets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pika.pokedex.domain.util.toHexColor

/**
 * Color Palette composable with list of colors displayed horizontally
 * @param visible Visibility of the List
 * @param colorList Requires List of [Color]
 * @param selectedColor Requires the currently selected Color
 * @param onSelectColor on SelectColor lambda triggered when a color is selected
 */
@Composable
fun ColorPalette(
    visible: Boolean,
    colorList: List<Color>,
    selectedColor: Color,
    onSelectColor: (String) -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(durationMillis = 350)
        ) + slideInHorizontally(
            animationSpec = tween(durationMillis = 350)
        ),
        exit = fadeOut(
            animationSpec = tween(durationMillis = 350)
        ) + slideOutHorizontally(
            animationSpec = tween(durationMillis = 350)
        )
    ) {
        Surface(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .wrapContentSize(Alignment.Center),
            shape = RoundedCornerShape(20.dp),
            color = Color.White.copy(alpha = 0.3f)
        ) {
            Row(
                modifier = Modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                colorList.forEach { color ->
                    ColorCard(
                        selectedColor = selectedColor,
                        color = color,
                        onSelectColor = {
                            onSelectColor(it.toHexColor())
                        }
                    )
                }
            }
        }
    }
}

/**
 * Color Card composable to display Pokemon Colors
 * @param color The Color of the Card
 * @param onSelectColor The selected color on Card click
 */
@Composable
private fun ColorCard(
    selectedColor: Color,
    color: Color,
    onSelectColor: (Color) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Surface(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .size(50.dp)
            .clickable(
                indication = null,
                interactionSource = interactionSource,
                onClick = {
                    onSelectColor(color)
                }
            ),
        shape = CircleShape,
        color = color,
        border = BorderStroke(
            width = 2.dp,
            color = if (selectedColor == color) MaterialTheme.colorScheme.background else Color.Transparent
        ),
        content = { }
    )
}