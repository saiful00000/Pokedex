package com.shaiful.pokedex.screens.pokemon_list.viewmodels

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.shaiful.pokedex.data.remote.responses.pokemon_detail.Pokemon
import com.shaiful.pokedex.repository.PokemonRepository
import com.shaiful.pokedex.screens.pokemon_list.models.PokemonListEntry
import com.shaiful.pokedex.util.ResData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val perPage = 10
    private var currentPage = 0

    var pokemonList = mutableStateOf<List<PokemonListEntry>>(emptyList())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    fun loadPokemonPaginated() {
        viewModelScope.launch {

            isLoading.value = true

            val result = pokemonRepository.getPokemonList(
                limit = perPage,
                offset = currentPage * perPage
            )

            when (result) {
                is ResData.Error -> {
                    endReached.value = currentPage * perPage >= result.data!!.count

                    val pokemonEntries = result.data.results.mapIndexed { index, entry ->
                        val number = if(entry.url.endsWith("/")) {
                            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                        } else {
                            entry.url.takeLastWhile { it.isDigit() }
                        }

                        val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                        PokemonListEntry(
                            pokemonName = entry.name.capitalize(Locale.ROOT),
                            imageUrl = url,
                            number = number.toInt(),
                        )

                    }

                    currentPage ++;
                    loadError.value = ""
                    isLoading.value = false

                    pokemonList.value += pokemonEntries
                }
                is ResData.Success -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }

                is ResData.Loading -> TODO()
            }
        }
    }

    fun calcDominantColor (drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let {colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

}