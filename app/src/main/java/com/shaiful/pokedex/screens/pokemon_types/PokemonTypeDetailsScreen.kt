package com.shaiful.pokedex.screens.pokemon_types

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shaiful.pokedex.composables.CommonAppBar
import com.shaiful.pokedex.screens.pokemon_types.viewmodels.PokemonTypesViewModel

@Composable
fun PokemonTypeDetailsScreen (
    navController: NavController,
    pokemonTypesViewModel: PokemonTypesViewModel = hiltViewModel(),
    pokemonType: String
) {

    pokemonTypesViewModel.loadPokemonTypeDetail(pokemonType)

    Scaffold (
        topBar = { CommonAppBar(title = pokemonType, navController = navController) }
    ) {
        Column (
            modifier = Modifier.padding(it)
        ) {

        }
    }
}