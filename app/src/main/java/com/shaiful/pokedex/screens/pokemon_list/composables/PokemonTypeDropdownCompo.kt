//package com.shaiful.pokedex.screens.pokemon_list.composables
//
//import androidx.compose.foundation.border
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.KeyboardArrowDown
//import androidx.compose.material3.DropdownMenu
//import androidx.compose.material3.DropdownMenuItem
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import com.shaiful.pokedex.screens.pokemon_list.models.PokemonType
//import com.shaiful.pokedex.screens.pokemon_list.viewmodels.PokemonListViewModel
//
//@Composable
//fun PokemonTypeDropDownCompo(
//    pokemonListViewModel: PokemonListViewModel
//) {
//
//    var pokemonTypes by remember { pokemonListViewModel.pokemonTypeList }
//
//    var selectedItem: PokemonType? by remember { mutableStateOf(null) }
//    var expanded by remember { mutableStateOf(false) }
//
//    Box(
//        modifier = Modifier
//            .padding(16.dp)
//            .height(50.dp)
//            .fillMaxWidth()
//            .clickable {
//                expanded = !expanded
//            }
//            .border(
//                width = 2.dp,
//                color = Color.Black,
//                shape = RoundedCornerShape(5.dp)
//            )
//            .padding(horizontal = 16.dp)
//    ) {
//        Row (
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Start,
//            modifier = Modifier
//                .fillMaxHeight()
//        ) {
//            Text(
//                text = selectedItem?.type ?: "Select Type",
//                style = TextStyle(color = Color.Black, fontWeight = FontWeight.Medium),
//                modifier = Modifier
//                    .weight(1f)
//            )
//            Icon(Icons.Default.KeyboardArrowDown, contentDescription = "dropdown_icon")
//        }
//    }
//
//    DropdownMenu(
//        expanded = expanded,
//        onDismissRequest = {
//            expanded = false
//        }
//    ) {
//        pokemonTypes.forEach { type ->
//            DropdownMenuItem(
//                text = {
//                    Text(text = type.type)
//                },
//                onClick = {
//                    expanded = false
//                    selectedItem = type
//                    pokemonListViewModel.filterByPokemonType(type)
//                }
//            )
//        }
//    }
//}