package com.shaiful.pokedex.screens.pokemon_types.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaiful.pokedex.data.remote.responses.pokemon_type_details.PokemonTypeDetails
import com.shaiful.pokedex.repository.PokemonRepository
import com.shaiful.pokedex.screens.pokemon_list.models.PokemonType
import com.shaiful.pokedex.util.ResData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PokemonTypesViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
): ViewModel() {

    /*
    * Pokemon type related fields
    * */
    var pokemonTypeList = mutableStateOf<List<PokemonType>>(emptyList())
    var isTypesLoading = mutableStateOf(true)

    init {
        loadPokemonTypes()
    }

    private fun loadPokemonTypes() {
        viewModelScope.launch {

            val result = pokemonRepository.getPokemonTypes()
            isTypesLoading.value = false

            when (result) {
                is ResData.Success -> {
                    val types = result.data?.results?.mapIndexed{ _, type ->
                        PokemonType(
                            type = type.name,
                            url = type.url
                        )
                    }

                    pokemonTypeList.value += types ?: emptyList()
                }
                is ResData.Error -> {}
                is ResData.Loading -> {}
            }

            isTypesLoading.value = false
        }
    }

}