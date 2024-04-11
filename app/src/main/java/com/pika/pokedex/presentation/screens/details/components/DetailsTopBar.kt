package com.pika.pokedex.presentation.screens.details.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pika.pokedex.R
import com.pika.pokedex.presentation.components.CardBubble
import com.pika.pokedex.presentation.ui.theme.mPlus

/**
 * Details TopBar composable for Details Screen
 * @param id Requires selected Pokemon id
 * @param name Requires name of the selected Pokemon
 * @param type Requires Pokemon type
 * @param category Requires Pokemon category
 * @param onBackPressed On Back press lambda triggered when the Back button is pressed
 */
@Composable
fun DetailsTopBar(
    visible: Boolean,
    id: String,
    name: String,
    type: String,
    category: String,
    onDeletePressed: () -> Unit,
    onBackPressed: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = interactionSource,
                    onClick = onBackPressed
                ),
                painter = painterResource(id = R.drawable.back),
                tint = Color.White,
                contentDescription = stringResource(R.string.back)
            )

            Icon(
                modifier = Modifier.clickable(
                    indication = null,
                    interactionSource = interactionSource,
                    onClick = onDeletePressed
                ),
                painter = painterResource(id = R.drawable.delete),
                tint = Color.White,
                contentDescription = stringResource(id = R.string.delete_pokemon)
            )
        }

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
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = name,
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = mPlus,
                        color = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "#$id",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = mPlus,
                        color = Color.White
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    CardBubble(text = type, fontSize = 16)
                    CardBubble(text = category, fontSize = 16)
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0XFF00AFFF
)
@Composable
private fun DetailsTopBarPreview() {
    DetailsTopBar(
        visible = true,
        id = "66112f054178e032a297fc54",
        name = "Pikachu",
        type = "Electric",
        category = "Mouse",
        onDeletePressed = { },
        onBackPressed = { }
    )
}