package com.shaiful.pokedex.repository

import com.shaiful.pokedex.data.remote.PokeApi
import com.shaiful.pokedex.data.remote.responses.pokemon_detail.Pokemon
import com.shaiful.pokedex.data.remote.responses.pokemon_list.PokemonList
import com.shaiful.pokedex.data.remote.responses.pokemon_types.PokemonTypeResponse
import com.shaiful.pokedex.util.ResData
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class PokemonRepository @Inject constructor(
    private val api: PokeApi
){
    suspend fun getPokemonList(limit: Int, offset:  Int) : ResData<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch (e: Exception) {
            return  ResData.Error(message = e.toString())
        }

        return ResData.Success(response)
    }

    suspend fun getPokemonDetail(name: String) : ResData<Pokemon> {
        val response = try {
            api.getPokemonDetail(name)
        } catch (e: Exception) {
            return  ResData.Error(message = e.toString())
        }

        return ResData.Success(response)
    }

    suspend fun getPokemonTypes() : ResData<PokemonTypeResponse> {
        val response = try {
            api.getPokemonTypes()
        } catch (e: Exception) {
            return  ResData.Error(message = e.toString())
        }

        return  ResData.Success(response)
    }
}