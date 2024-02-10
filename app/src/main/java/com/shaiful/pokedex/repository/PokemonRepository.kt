package com.shaiful.pokedex.repository

import com.shaiful.pokedex.data.remote.PokeApi
import com.shaiful.pokedex.data.remote.responses.pokemon_detail.Pokemon
import com.shaiful.pokedex.data.remote.responses.pokemon_list.PokemonList
import com.shaiful.pokedex.data.remote.responses.pokemon_type_details.PokemonTypeDetails
import com.shaiful.pokedex.data.remote.responses.pokemon_type_details.TypeMove
import com.shaiful.pokedex.data.remote.responses.pokemon_types.PokemonTypeResponse
import com.shaiful.pokedex.screens.pokemon_list.models.PokemonListEntry
import com.shaiful.pokedex.util.ResData
import dagger.hilt.android.scopes.ActivityScoped
import timber.log.Timber
import java.util.Objects
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
) {
    suspend fun getPokemonList(limit: Int, offset: Int): ResData<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return ResData.Error(message = e.toString())
        }

        return ResData.Success(response)
    }

    suspend fun getPokemonDetail(name: String): ResData<Pokemon> {
        val response = try {
            api.getPokemonDetail(name)
        } catch (e: Exception) {
            return ResData.Error(message = e.toString())
        }

        return ResData.Success(response)
    }

    suspend fun getPokemonTypes(): ResData<PokemonTypeResponse> {
        val response = try {
            api.getPokemonTypes()
        } catch (e: Exception) {
            return ResData.Error(message = e.toString())
        }

        return ResData.Success(response)
    }

    suspend fun getTypeDetail(typeName: String): ResData<PokemonTypeDetails> {

        val data = try {
            val response = api.getPokemonTypeDetail(typeName)

            if (response.isEmpty()) {
                return ResData.Error(message = "No Data!")
            }

            //
            // parse pokmons from type details json
            val pokemonJsonList = response["pokemon"] as? List<Map<String, *>>

            val pokemonList = pokemonJsonList?.map {
                val pokemonInfo = it["pokemon"] as? Map<String, *>
                val url = (pokemonInfo?.get("url") as? String?) ?: "";

                val number = if(url.endsWith("/")) {
                    url.dropLast(1).takeLastWhile { it.isDigit() }
                } else {
                    url.takeLastWhile { it.isDigit() }
                }

                val imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"

                PokemonListEntry(
                    number = (it["slot"] as? Int?) ?: 0,
                    imageUrl = imageUrl,
                    pokemonName = (pokemonInfo?.get("name") as? String?) ?: "",
                )
            }?.toList() ?: emptyList()

            //
            // parse move list from pokemon type details
            val moveJsonList = response["moves"] as? List<Map<String, String>>?
            val moveList = moveJsonList?.map {
                TypeMove(
                    name = it.get("name") ?: "",
                    url = it.get("url") ?: "",
                )
            }?.toList() ?: emptyList()

            PokemonTypeDetails(
                id = (response.get("id") as? Int?) ?: 0,
                name = (response.get("name") as? String?) ?: "",
                pokemonList = pokemonList,
                moveList = moveList,
            )
        } catch (e: Exception) {
            Timber.e(e)
            return ResData.Error(message = e.toString())
        }

        return ResData.Success(data)
    }
}