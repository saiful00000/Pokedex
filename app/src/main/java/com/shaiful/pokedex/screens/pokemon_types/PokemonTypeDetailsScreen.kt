package com.shaiful.pokedex.screens.pokemon_types

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shaiful.pokedex.composables.CommonAppBar
import com.shaiful.pokedex.composables.ErrorCompo
import com.shaiful.pokedex.composables.LoadingCompo
import com.shaiful.pokedex.screens.pokemon_list.composables.PokemonListEntryCompo
import com.shaiful.pokedex.screens.pokemon_types.viewmodels.PokemonTypeDetailViewModel
import com.shaiful.pokedex.screens.pokemon_types.viewmodels.PokemonTypesViewModel
import timber.log.Timber

@Composable
fun PokemonTypeDetailsScreen(
    navController: NavController,
    pokemonTypeDetailViewModel: PokemonTypeDetailViewModel = hiltViewModel(),
    pokemonType: String
) {

    pokemonTypeDetailViewModel.loadPokemonTypeDetail(pokemonType)

    val typeDetail by remember { pokemonTypeDetailViewModel.pokemonTypeDetails }
    val typeDetailLoading by remember { pokemonTypeDetailViewModel.isTypeDetailsLoading }
    val loadError by remember { pokemonTypeDetailViewModel.loadError }

    Scaffold(topBar = {
        CommonAppBar(
            title = pokemonType.replaceFirstChar { it.uppercaseChar() },
            navController = navController
        )
    }) {

        if (typeDetailLoading) {
            LoadingCompo(message = "Type is loading...")
        } else if(loadError != null) {
            ErrorCompo(message = loadError)
        } else {
            Timber.i("Pokemons = ${typeDetail?.pokemonList?.size}")

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                modifier = Modifier.padding(it)
            ) {
                items(typeDetail?.pokemonList?.size ?: 0) {
                    PokemonListEntryCompo(
                        entry = typeDetail!!.pokemonList[it],
                        navController = navController
                    )
                }
            }
        }
    }
}