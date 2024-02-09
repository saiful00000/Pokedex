package com.shaiful.pokedex.screens.pokemon_types

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shaiful.pokedex.composables.CommonAppBar
import com.shaiful.pokedex.composables.LoadingCompo
import com.shaiful.pokedex.screens.pokemon_types.composables.TypeListEntryCompo
import com.shaiful.pokedex.screens.pokemon_types.viewmodels.PokemonTypesViewModel

@Composable
fun PokemonTypesScreen(
    navController: NavController,
    pokemonTypesViewModel: PokemonTypesViewModel = hiltViewModel()
) {

    val pokemonTypeList by remember { pokemonTypesViewModel.pokemonTypeList }
    val typeLoading by remember { pokemonTypesViewModel.isTypesLoading }

    Scaffold(
        topBar = { CommonAppBar(title = "All Types", navController = navController) }
    ) {

        if (typeLoading) {
            LoadingCompo(message = "Loading types...")
        }

        Column(
            modifier = Modifier.padding(it)
        ) {
            LazyVerticalGrid(
                GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp)
            ) {
                itemsIndexed(pokemonTypeList) {index, it ->
                    TypeListEntryCompo(
                        navController = navController,
                        type = it,
                        index = index,
                    )
                }
            }
        }
    }
}