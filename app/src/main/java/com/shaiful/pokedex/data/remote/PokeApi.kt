package com.shaiful.pokedex.data.remote

import com.shaiful.pokedex.data.remote.responses.pokemon_detail.Pokemon
import com.shaiful.pokedex.data.remote.responses.pokemon_list.PokemonList
import com.shaiful.pokedex.data.remote.responses.pokemon_types.PokemonTypeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Objects

interface PokeApi {

    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): PokemonList

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name : String
    ): Pokemon

    @GET("type")
    suspend fun getPokemonTypes() : PokemonTypeResponse

    @GET("type/{name}")
    suspend fun getPokemonTypeDetail(
        @Path("name") name: String
    ) : Map<String, *>

}