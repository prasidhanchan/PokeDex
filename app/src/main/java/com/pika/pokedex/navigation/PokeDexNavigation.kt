package com.pika.pokedex.navigation

import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pika.pokedex.R
import com.pika.pokedex.domain.models.Pokemon
import com.pika.pokedex.presentation.screens.details.DetailsScreen
import com.pika.pokedex.presentation.screens.details.DetailsViewModel
import com.pika.pokedex.presentation.screens.home.HomeScreen
import com.pika.pokedex.presentation.screens.home.HomeViewModel
import com.pika.pokedex.presentation.screens.upsert.UpsertScreen
import com.pika.pokedex.presentation.screens.upsert.UpsertViewModel
import kotlinx.coroutines.delay

@Composable
fun PokeDexNavigation(viewModelHome: HomeViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    NavHost(
        startDestination = Routes.HOMESCREEN.name,
        navController = navController
    ) {
        composable(route = Routes.HOMESCREEN.name) {
            val uiState by viewModelHome.uiState.collectAsState()
            var visible by rememberSaveable { mutableStateOf(false) }

            LaunchedEffect(key1 = Unit) {
                delay(800L)
                visible = true
            }

            HomeScreen(
                visible = visible,
                uiState = uiState,
                navigateToDetailsWithArgs = { pokemon ->
                    val encodedUrl = Uri.encode(pokemon.image)
                    navController.navigate(
                        route = Routes.DETAILSSCREEN.name +
                                "/${pokemon._id}/${pokemon.name}/${pokemon.description}" +
                                "/${pokemon.type}/${pokemon.category}/${pokemon.height}" +
                                "/${pokemon.weight}/${encodedUrl}/${pokemon.color}"
                    )
                },
                navigateToUpsert = { navController.navigate(Routes.UPSERTSCREEN.name) }
            )
        }

        composable(
            route = "${Routes.DETAILSSCREEN.name}/{id}/{name}/{description}/{type}/{category}/{height}/{weight}/{image}/{color}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                },
                navArgument("name") {
                    type = NavType.StringType
                },
                navArgument("description") {
                    type = NavType.StringType
                },
                navArgument("type") {
                    type = NavType.StringType
                },
                navArgument("category") {
                    type = NavType.StringType
                },
                navArgument("height") {
                    type = NavType.StringType
                },
                navArgument("weight") {
                    type = NavType.StringType
                },
                navArgument("image") {
                    type = NavType.StringType
                },
                navArgument("color") {
                    type = NavType.StringType
                }
            )
        ) { backStack ->
            val viewModel: DetailsViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            val id = backStack.arguments?.getString("id")
            val name = backStack.arguments?.getString("name")
            val description = backStack.arguments?.getString("description")
            val type = backStack.arguments?.getString("type")
            val category = backStack.arguments?.getString("category")
            val height = backStack.arguments?.getString("height")
            val weight = backStack.arguments?.getString("weight")
            val image = backStack.arguments?.getString("image")
            val color = backStack.arguments?.getString("color")

            val pokemon = Pokemon(
                _id = id,
                name = name!!,
                description = description!!,
                type = type!!,
                category = category!!,
                height = height!!,
                weight = weight!!,
                image = image!!,
                color = color!!
            )

            var visible by remember { mutableStateOf(false) }

            LaunchedEffect(key1 = Unit) {
                delay(200L)
                visible = true
            }

            val context = LocalContext.current

            DetailsScreen(
                visible = visible,
                pokemon = pokemon,
                onDeletePressed = {
                    viewModel.removePokemon(
                        id = id!!,
                        name = name,
                        onSuccess = {
                            Toast.makeText(
                                context,
                                context.getString(R.string.pokemon_removed),
                                Toast.LENGTH_LONG
                            ).show()

                            viewModelHome.getAllPokemon()
                            navController.popBackStack()
                        },
                        onError = { error ->
                            Toast.makeText(
                                context,
                                error,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    )
                },
                onUpdatePressed = {
                    val encodedUrl = Uri.encode(image)
                    navController.navigate(
                        route = Routes.UPSERTSCREEN.name +
                                "?/${id}/${name}/${description}/${type}/${category}/${height}/${weight}/${encodedUrl}/${color}"
                    )
                },
                onBackPressed = { navController.popBackStack() }
            )
        }

        composable(
            route = "${Routes.UPSERTSCREEN.name}?/{id}/{name}/{description}/{type}/{category}/{height}/{weight}/{image}/{color}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("name") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("description") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("type") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("category") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("height") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("weight") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("image") {
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("color") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { backStack ->
            val viewModel: UpsertViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            val id = backStack.arguments?.getString("id")
            val name = backStack.arguments?.getString("name")
            val description = backStack.arguments?.getString("description")
            val type = backStack.arguments?.getString("type")
            val category = backStack.arguments?.getString("category")
            val height = backStack.arguments?.getString("height")
            val weight = backStack.arguments?.getString("weight")
            val image = backStack.arguments?.getString("image")
            val color = backStack.arguments?.getString("color")

            val pokemon = Pokemon(
                _id = id,
                name = uiState.nameState,
                description = uiState.descriptionState,
                type = uiState.typeState,
                category = uiState.categoryState,
                height = uiState.heightState,
                weight = uiState.weightState,
                image = uiState.imageState,
                color = uiState.colorState
            )

            val context = LocalContext.current

            var visible by remember { mutableStateOf(false) }

            LaunchedEffect(key1 = Unit) {
                delay(200L)
                visible = true
            }

            LaunchedEffect(key1 = Unit) {
                if (!id.isNullOrBlank()) {
                    viewModel.setId(value = id)
                    viewModel.setName(value = name!!)
                    viewModel.setDescription(value = description!!)
                    viewModel.setType(value = type!!)
                    viewModel.setCategory(value = category!!)
                    viewModel.setHeight(value = height!!)
                    viewModel.setWeight(value = weight!!)
                    viewModel.setImage(value = Uri.decode(image)!!)
                    viewModel.setColor(value = color!!)
                }
            }

            UpsertScreen(
                visible = visible,
                uiState = uiState,
                onValueChangeName = { viewModel.setName(value = it) },
                onValueChangeDes = { viewModel.setDescription(value = it) },
                onValueChangeType = { viewModel.setType(value = it) },
                onValueChangeCategory = { viewModel.setCategory(value = it) },
                onValueChangeHeight = { viewModel.setHeight(value = it) },
                onValueChangeWeight = { viewModel.setWeight(value = it) },
                onValueChangeColor = { viewModel.setColor(value = it) },
                onValueChangeImage = { viewModel.setImage(value = it) },
                onButtonPressed = {
                    if (pokemon._id != null) {
                        viewModel.updatePokemon(
                            id = id!!,
                            onSuccess = {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.pokemon_updated),
                                    Toast.LENGTH_LONG
                                ).show()

                                navController.navigate(Routes.HOMESCREEN.name) {
                                    navController.graph.findStartDestination().route?.let { route ->
                                        popUpTo(route)
                                    }
                                }
                                viewModelHome.getAllPokemon()
                            },
                            onError = { error ->
                                Toast.makeText(
                                    context,
                                    error,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        )
                    } else {
                        if (uiState.nameState.isNotEmpty() &&
                            uiState.descriptionState.isNotEmpty() &&
                            uiState.typeState.isNotEmpty() &&
                            uiState.categoryState.isNotEmpty() &&
                            uiState.heightState.isNotEmpty() &&
                            uiState.weightState.isNotEmpty() &&
                            uiState.colorState.isNotEmpty()
                        ) {
                            if (uiState.imageState?.isNotEmpty() == true) {
                                viewModel.addPokemon(
                                    onSuccess = {
                                        Toast.makeText(
                                            context,
                                            context.getString(R.string.pokemon_added),
                                            Toast.LENGTH_LONG
                                        ).show()

                                        navController.popBackStack()
                                        viewModelHome.getAllPokemon()
                                    },
                                    onError = { error ->
                                        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                                    }
                                )
                            } else {
                                Toast.makeText(
                                    context,
                                    context.getString(R.string.please_select_an_image),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                context,
                                context.getString(R.string.please_fill_all_fields),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            ) { navController.popBackStack() }
        }
    }
}