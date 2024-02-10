package com.shaiful.pokedex.screens.pokemon_types.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaiful.pokedex.data.remote.responses.pokemon_type_details.PokemonTypeDetails
import com.shaiful.pokedex.repository.PokemonRepository
import com.shaiful.pokedex.util.ResData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonTypeDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    var pokemonTypeDetails = mutableStateOf<PokemonTypeDetails?>(null)
    val isTypeDetailsLoading = mutableStateOf(true)
    val loadError = mutableStateOf<String?>(null)

    fun loadPokemonTypeDetail(pokemonType: String) {
        viewModelScope.launch {

            isTypeDetailsLoading.value = true;

            val result = pokemonRepository.getTypeDetail(pokemonType)

            when (result) {
                is ResData.Success -> {
                    pokemonTypeDetails.value = result.data
                }

                is ResData.Error -> {
                    loadError.value = result.message
                }
                is ResData.Loading -> {}
            }

            isTypeDetailsLoading.value = false;

        }
    }
}