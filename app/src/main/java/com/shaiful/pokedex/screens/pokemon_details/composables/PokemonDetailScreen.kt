package com.shaiful.pokedex.screens.pokemon_details.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.size.Size
import com.shaiful.pokedex.screens.pokemon_details.viewmodels.PokemonDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    navController: NavController,
    pokemonName: String?,
    pokemonDetailViewModel: PokemonDetailViewModel = hiltViewModel()
) {

    pokemonDetailViewModel.loadPokemonDetail(pokemonName ?: "Shaiful")

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Poke: ${pokemonName ?: "-"}",
                        textAlign = TextAlign.Center,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back ICon",
                        )
                    }
                }
            )
        },
        content = {

            val pokemon by remember { pokemonDetailViewModel.pokemon }
            val isLoading by remember { pokemonDetailViewModel.isLoading }

            if(isLoading) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        color = Color.Red
                    )
                }
            }else {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues = it)
                ) {
                    Text(text = ("Name: " + pokemon?.name) ?: "N/A")
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = ("Height: " + pokemon?.height?.toString()) ?: "N/A")
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = ("Weight" + pokemon?.weight?.toString()) ?: "N/A")
                }
            }
        }
    )
}