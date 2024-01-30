package com.shaiful.pokedex.data.remote.responses.pokemon_types

data class PokemonTypeResponse(
    val count: Int,
    val next: Any,
    val previous: Any,
    val results: List<Result>
)