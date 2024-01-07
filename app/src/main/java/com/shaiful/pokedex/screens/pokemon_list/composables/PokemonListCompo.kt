package com.shaiful.pokedex.screens.pokemon_list.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shaiful.pokedex.screens.pokemon_list.models.PokemonListEntry
import com.shaiful.pokedex.screens.pokemon_list.viewmodels.PokemonListViewModel

@Composable
fun PokemonListCompo() {

}

@Composable
fun PokemonListItemCompo(
    entry: PokemonListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    pokemonListViewModel: PokemonListViewModel = hiltViewModel()
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor = remember {
        mutableStateOf(defaultDominantColor)
    }
}