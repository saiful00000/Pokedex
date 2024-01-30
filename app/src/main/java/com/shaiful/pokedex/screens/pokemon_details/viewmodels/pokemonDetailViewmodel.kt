package com.shaiful.pokedex.screens.pokemon_details.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaiful.pokedex.data.remote.responses.pokemon_detail.Pokemon
import com.shaiful.pokedex.repository.PokemonRepository
import com.shaiful.pokedex.util.ResData
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
) : ViewModel() {
    var pokemon = mutableStateOf<Pokemon?>(null)
    var isLoading = mutableStateOf(true)

    fun loadPokemonDetail(pokemonName: String) {
        viewModelScope.launch {
            isLoading.value = true

            when (val result = pokemonRepository.getPokemonDetail(pokemonName.lowercase())) {
                is ResData.Success -> {
                    pokemon.value = result.data
                    isLoading.value = false
                }

                is ResData.Error -> {
                    isLoading.value = false
                }

                is ResData.Loading -> {

                }
            }
        }
    }
}