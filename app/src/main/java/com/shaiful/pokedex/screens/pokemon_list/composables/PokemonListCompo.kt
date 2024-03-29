package com.shaiful.pokedex.screens.pokemon_list.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.shaiful.pokedex.composables.ErrorCompo
import com.shaiful.pokedex.composables.LoadingCompo
import com.shaiful.pokedex.navigation.RouteNames
import com.shaiful.pokedex.screens.pokemon_list.models.PokemonListEntry
import com.shaiful.pokedex.screens.pokemon_list.viewmodels.PokemonListViewModel

@Composable
fun PokemonListCompo(
    navController: NavController,
    pokemonListViewModel: PokemonListViewModel
) {
    val pokemonList by remember { pokemonListViewModel.pokemonList }
    val endReached by remember { pokemonListViewModel.endReached }
    val loadError by remember { pokemonListViewModel.loadError }
    val isLoading by remember { pokemonListViewModel.isLoading }



    if(isLoading) {
        LoadingCompo(message = "Pokemons loading...")
    } else if (loadError.isNotEmpty()) {
        ErrorCompo(message = loadError)
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(pokemonList.size) {

                if (it >= pokemonList.size - 1 && !endReached) {
                    pokemonListViewModel.loadPokemonPaginated()
                }

                PokemonListEntryCompo(
                    entry = pokemonList[it],
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun PokedexRow(
    rowIndex: Int,
    entries: List<PokemonListEntry>,
    navController: NavController
) {
    Column {
        Row {
            PokemonListEntryCompo(
                entry = entries[rowIndex * 2],
                navController = navController,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            if (entries.size >= rowIndex * 2 + 2) {
                PokemonListEntryCompo(
                    entry = entries[rowIndex * 2 + 1],
                    navController = navController,
                    modifier = Modifier.weight(1f)
                )
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun PokemonListEntryCompo(
    entry: PokemonListEntry,
    navController: NavController,
    modifier: Modifier = Modifier,
    pokemonListViewModel: PokemonListViewModel = hiltViewModel()
) {
    val defaultDominantColor = MaterialTheme.colorScheme.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(8.dp)
            .shadow(5.dp, RoundedCornerShape(10.dp))
            .clip(RoundedCornerShape(10.dp))
            .aspectRatio(1f)
            .width(100.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(dominantColor, defaultDominantColor)
                )
            )
            .clickable {
                navController.navigate(
                    "${RouteNames.pokemonDetailScreen}/${dominantColor.toArgb()}/${entry.pokemonName}"
                )
            }
    ) {
        Column {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(entry.imageUrl)
//                   .target() {
//                       pokemonListViewModel.calcDominantColor(it) {color ->
//                           dominantColor = color
//                       }
//                   }
                    .crossfade(true)
                    .build(),
                contentDescription = "entry_pokemon_image",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                text = entry.pokemonName.replaceFirstChar { it.uppercaseChar() },
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}