package com.pika.pokedex.presentation.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pika.pokedex.R
import com.pika.pokedex.domain.models.Pokemon
import com.pika.pokedex.presentation.components.PokeDialog
import com.pika.pokedex.presentation.screens.details.components.DetailsSheet
import com.pika.pokedex.presentation.screens.details.components.DetailsTopBar
import kotlinx.coroutines.delay

@Composable
fun DetailsScreen(
    visible: Boolean = false,
    pokemon: Pokemon,
    onDeletePressed: () -> Unit,
    onUpdatePressed: () -> Unit,
    onBackPressed: () -> Unit,
) {
    var isVisible by remember { mutableStateOf(visible) }

    LaunchedEffect(key1 = Unit) {
        delay(200L)
        isVisible = true
    }

    var isDialogVisible by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(pokemon.color?.substring(2)?.toLong(16)!!)
    ) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokemon_details_bg), 
                contentDescription = stringResource(id = R.string.background_image)
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    DetailsTopBar(
                        visible = isVisible,
                        id = pokemon._id!!,
                        name = pokemon.name!!,
                        type = pokemon.type!!,
                        category = pokemon.category!!,
                        onDeletePressed = { isDialogVisible = true },
                        onBackPressed = onBackPressed
                    )
                }

                DetailsSheet(
                    pokemon = pokemon,
                    visible = isVisible,
                    onUpdatePressed = onUpdatePressed
                )

                AsyncImage(
                    modifier = Modifier
                        .padding(bottom = 100.dp)
                        .fillMaxHeight()
                        .size(250.dp),
                    model = pokemon.image,
                    contentDescription = pokemon.name
                )
            }
        }
    }

    PokeDialog(
        visible = isDialogVisible,
        title = stringResource(R.string.delete_pokemon),
        subTitle = stringResource(R.string.delete_pokemon_message),
        onConfirm = {
            onDeletePressed()
            isDialogVisible = false
        },
        onDismiss = { isDialogVisible = false }
    )
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    DetailsScreen(
        visible = true,
        pokemon = Pokemon(
            _id = "66112f054178e032a297fc54",
            name = "Bulbasaur",
            image = "",
            description = "For some time after its birth, it uses the nutrients that are packed into the seed on its back in order to grow.",
            type = "Grass",
            category = "Seed",
            height = "2'04",
            weight = "15.2",
            color = "0XFF24CC43"
        ),
        onDeletePressed = { },
        onUpdatePressed = { },
        onBackPressed = { }
    )
}