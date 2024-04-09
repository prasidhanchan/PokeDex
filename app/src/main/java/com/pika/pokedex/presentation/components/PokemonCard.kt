package com.pika.pokedex.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.pika.pokedex.R

/**
 * Pokemon Card composable with different colors as per the pokemon
 * @param modifier Requires [Modifier]
 * @param name Requires the name of the Pokemon
 * @param type Requires Pokemon type i.e Fire, Normal, etc
 * @param height Requires the height of the pokemon
 * @param weight Requires the weight of the pokemon
 * @param color Requires the color to be applied to the Card
 * @param onClick The on  click lambda of the Card
 */
@Composable
fun PokemonCard(
    modifier: Modifier = Modifier,
    delay: Int,
    visible: Boolean,
    name: String,
    image: String,
    type: String,
    height: String,
    weight: String,
    color: String,
    onClick: () -> Unit
) {
    val colorLong = color.substring(2).toLong(16)

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = delay
            )
        ),
        exit = fadeOut(
            animationSpec = tween(
                durationMillis = 1000
            )
        )
    ) {
        Surface(
            modifier = modifier
                .padding(vertical = 5.dp, horizontal = 10.dp)
                .fillMaxWidth()
                .height(160.dp)
                .animateContentSize(
                    animationSpec = tween(durationMillis = 1000)
                )
                .clickable(onClick = onClick),
            shape = RoundedCornerShape(15.dp),
            color = Color(colorLong)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pokemon_card_bg),
                    contentDescription = stringResource(R.string.pokemon_background)
                )
                Row(
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxHeight(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = name,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        CardBubble(
                            text = type
                        )

                        Row(
                            modifier = Modifier.fillMaxHeight(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            CardBubble(
                                text = height
                            )
                            CardBubble(
                                text = stringResource(R.string.weight_in_lbs, weight)
                            )
                        }
                    }

                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = image,
                        contentScale = ContentScale.FillBounds,
                        contentDescription = name
                    )
                }
            }
        }
    }
}

@Preview(
    heightDp = 180
)
@Composable
private fun PokemonCardPreview() {
    PokemonCard(
        delay = 100,
        visible = true,
        name = "Bulbasaur",
        image = "",
        type = "Grass",
        height = "2'04",
        weight = "15.2",
        color = "0XFF24CC43",
        onClick = { }
    )
}