package com.shaiful.pokedex.screens.pokemon_list.viewmodels

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.shaiful.pokedex.repository.PokemonRepository
import com.shaiful.pokedex.screens.pokemon_list.models.PokemonListEntry
import com.shaiful.pokedex.screens.pokemon_list.models.PokemonType
import com.shaiful.pokedex.util.ResData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    /*
    * Pokemon list related fields
    * */
    private val perPage = 40
    private var currentPage = 0

    var pokemonList = mutableStateOf<List<PokemonListEntry>>(emptyList())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(true)
    var endReached = mutableStateOf(false)

    /*
    * Pokemon type related fields
    * */
    var pokemonTypeList = mutableStateOf<List<PokemonType>>(emptyList())
    var isTypesLoading = mutableStateOf(true)

    init {
        loadPokemonPaginated()
        loadPokemonTypes()
    }

    fun loadPokemonPaginated() {
        viewModelScope.launch {

            val result = pokemonRepository.getPokemonList(
                limit = perPage,
                offset = currentPage * perPage
            )

            when (result) {
                is ResData.Success -> {
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

                    Timber.tag("Pkemon list from api").i(pokemonEntries.size.toString());

                    currentPage ++;
                    loadError.value = ""
                    isLoading.value = false

                    pokemonList.value += pokemonEntries
                }
                is ResData.Error -> {
                    loadError.value = result.message ?: ""
                    isLoading.value = false
                }

                is ResData.Loading -> TODO()
            }
        }
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

    fun calcDominantColor (drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let {colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

    fun filterByPokemonType(type: PokemonType) {

    }

}