package com.pika.pokedex.presentation.screens.details.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pika.pokedex.R
import com.pika.pokedex.domain.models.Pokemon
import com.pika.pokedex.presentation.components.PokeButton

/**
 * Pokemon Bottom sheet composable to display the details of the selected Pokemon
 * @param modifier Requires [Modifier]
 * @param pokemon Requires selected [Pokemon]
 * @param visible Visibility of the Bottom sheet
 */
@Composable
fun DetailsSheet(
    modifier: Modifier = Modifier,
    pokemon: Pokemon,
    visible: Boolean,
    onUpdatePressed: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            animationSpec = tween(durationMillis = 350),
            initialOffsetY = { it }
        ),
        exit = slideOutVertically(
            animationSpec = tween(durationMillis = 350),
            targetOffsetY = { it }
        )
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            color = MaterialTheme.colorScheme.background,
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 80.dp, start = 30.dp, end = 30.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = pokemon.description!!,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                                )
                            ) {
                                append(stringResource(R.string.category))
                                append("        ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append(pokemon.category)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                                )
                            ) {
                                append(stringResource(R.string.height))
                                append("            ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append(pokemon.height)
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                                )
                            ) {
                                append(stringResource(R.string.weight))
                                append("           ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append(stringResource(id = R.string.weight_in_lbs, pokemon.weight!!))
                            }
                        }
                    )
                    
                    PokeButton(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(0.8f),
                        text = stringResource(id = R.string.update_pokemon),
                        onClick = onUpdatePressed
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.app_name),
                    style = TextStyle(
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(
    heightDp = 500
)
@Composable
private fun DetailsSheetPreview() {
    DetailsSheet(
        pokemon = Pokemon(
            _id = "1",
            name = "Pikachu",
            image = "",
            description = "When it is angered, it immediately discharges the energy stored in the pouches in its cheeks.",
            category = "Mouse",
            type = "Electric",
            color = "0XFFE7E41B",
            height = "1'04",
            weight = "13.2"
        ),
        visible = true,
        onUpdatePressed = { }
    )
}