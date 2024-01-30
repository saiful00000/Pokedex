package com.shaiful.pokedex.screens.pokemon_list.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.shaiful.pokedex.screens.pokemon_list.models.PokemonType
import com.shaiful.pokedex.screens.pokemon_list.viewmodels.PokemonListViewModel

@Composable
fun PokemonTypeDropDownCompo(
    pokemonListViewModel: PokemonListViewModel
) {

    var pokemonTypes by remember { pokemonListViewModel.pokemonTypeList }

    var selectedItem: PokemonType? by remember { mutableStateOf(null) }
    var expanded by remember { mutableStateOf(false) }

//    BasicTextField(
//        value = TextFieldValue(selectedItem?.type ?: "N/A"),
//        onValueChange = {},
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//            .clickable {
//                expanded = true
//            }
//    )

    OutlinedTextField(
        value = selectedItem?.type ?: "Select Type",
        onValueChange = {  },
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                // This value is used to assign to
                // the DropDown the same width
                //mTextFieldSize = coordinates.size.toSize()
            },
        label = {Text("Label")},
        trailingIcon = {
            Icon(Icons.Default.KeyboardArrowDown,"contentDescription",
                Modifier.clickable { expanded = !expanded })
        }
    )

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            expanded = false 
        }
    ) {
        pokemonTypes.forEach { type ->
            DropdownMenuItem(
                text = {
                    Text(text = type.type)
                },
                onClick = {
                    expanded = false
                }
            )
        }
    }
}