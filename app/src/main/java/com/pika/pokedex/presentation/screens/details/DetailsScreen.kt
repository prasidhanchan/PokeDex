package com.pika.pokedex.presentation.screens.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pika.pokedex.domain.models.Pokemon
import com.pika.pokedex.presentation.screens.details.components.DetailsSheet
import com.pika.pokedex.presentation.screens.details.components.DetailsTopBar

@Composable
fun DetailsScreen(
    visible: Boolean,
    pokemon: Pokemon,
    onBackPressed: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(pokemon.color?.substring(2)?.toLong(16)!!)
    ) { innerPadding ->
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
                    visible = visible,
                    _id = pokemon._id!!,
                    name = pokemon.name!!,
                    type = pokemon.type!!,
                    category = pokemon.category!!,
                    onBackPressed = onBackPressed
                )
            }

            DetailsSheet(
                pokemon = pokemon,
                visible = visible
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

@Preview
@Composable
private fun DetailsScreenContentPreview() {
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
        onBackPressed = { }
    )
}