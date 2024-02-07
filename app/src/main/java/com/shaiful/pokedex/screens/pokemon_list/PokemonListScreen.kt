package com.shaiful.pokedex.screens.pokemon_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.shaiful.pokedex.screens.pokemon_list.composables.MainAppBar
import com.shaiful.pokedex.screens.pokemon_list.composables.PokemonListCompo
import com.shaiful.pokedex.screens.pokemon_list.viewmodels.PokemonListViewModel

@Composable
fun PokemonListScreen(
    navController: NavController,
    pokemonListViewModel: PokemonListViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colorScheme.background, modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))

//            Image(
//                painter = painterResource(id = R.drawable.ic_international_pok_mon_logo),
//                contentDescription = "Pokemon",
//                modifier = Modifier
//                    .height(56.dp)
//                    .fillMaxWidth()
//            )
//            SearchBar(
//                hint = "Search...",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp)
//            ){
//
//            }
//            PokemonTypeDropDownCompo(pokemonListViewModel = pokemonListViewModel)
            MainAppBar(navController = navController)
            Spacer(modifier = Modifier.padding(4.dp))
            PokemonListCompo(
                navController = navController,
                pokemonListViewModel = pokemonListViewModel,
            )
        }
    }
}
