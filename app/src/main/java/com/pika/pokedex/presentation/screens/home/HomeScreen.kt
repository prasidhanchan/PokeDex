package com.pika.pokedex.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pika.pokedex.R
import com.pika.pokedex.domain.models.Pokemon
import com.pika.pokedex.domain.util.Constants.blue
import com.pika.pokedex.presentation.components.PokemonCard
import com.pika.pokedex.presentation.screens.home.components.HomeAppBar

@Composable
fun HomeScreen(
    visible: Boolean,
    uiState: UiState,
    navigateToDetailsWithArgs: (Pokemon) -> Unit,
    navigateToUpsert: () -> Unit,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(vertical = 40.dp),
                containerColor = blue,
                shape = CircleShape,
                onClick = navigateToUpsert
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    tint = Color.White,
                    contentDescription = stringResource(R.string.add_pokemon)
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokemon_bg),
                contentDescription = stringResource(R.string.background_image)
            )
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(all = 10.dp)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                HomeAppBar(modifier = Modifier.padding(vertical = 22.dp))
                uiState.pokemonList.forEachIndexed { index, pokemon ->
                    PokemonCard(
                        delay = index * 100,
                        visible = visible,
                        name = pokemon.name!!,
                        image = pokemon.image!!,
                        type = pokemon.type!!,
                        height = pokemon.height!!,
                        weight = pokemon.height,
                        color = pokemon.color!!,
                        onClick = { navigateToDetailsWithArgs(pokemon) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenContentPreview() {
    HomeScreen(
        visible = true,
        uiState = UiState(
            pokemonList = listOf(
                Pokemon(
                    _id = "1",
                    name = "Bulbasaur",
                    image = "",
                    description = "",
                    type = "Grass",
                    category = "",
                    height = "2'04",
                    weight = "15.2",
                    color = "0XFF24CC43"
                ),
                Pokemon(
                    _id = "2",
                    name = "Pikachu",
                    image = "",
                    description = "",
                    type = "Electric",
                    category = "",
                    height = "2'04",
                    weight = "15.2",
                    color = "0XFFCCEE5B"
                )
            )
        ),
        navigateToDetailsWithArgs = { },
        navigateToUpsert = { }
    )
}