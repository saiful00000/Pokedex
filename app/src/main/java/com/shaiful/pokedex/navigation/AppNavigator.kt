package com.shaiful.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.shaiful.pokedex.screens.pokemon_details.composables.PokemonDetailScreen
import com.shaiful.pokedex.screens.pokemon_list.PokemonListScreen
import com.shaiful.pokedex.screens.pokemon_types.PokemonTypesScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = RouteNames.startDestination) {

        composable(RouteNames.pokemonListScreen) {
            PokemonListScreen(navController = navController)
        }

        composable(
            RouteNames.pokemonDetailScreen + "/{dominantColor}/{pokemonName}",
            arguments = listOf(
                navArgument("dominantColor") {
                    type = NavType.IntType
                },
                navArgument("pokemonName") {
                    type = NavType.StringType
                }
            ),
        ) {
            // Get arguments
            val dominantColor = remember {
                val color = it.arguments?.getInt("dominantColor")
                color?.let { Color(it) } ?: Color.White
            }
            val pokemonName = remember {
                it.arguments?.getString("pokemonName")
            }

            PokemonDetailScreen(
                navController = navController,
                pokemonName = pokemonName
            )

        }

        composable(RouteNames.pokemonTypesScreen) {
            PokemonTypesScreen(navController = navController)
        }
    }
}