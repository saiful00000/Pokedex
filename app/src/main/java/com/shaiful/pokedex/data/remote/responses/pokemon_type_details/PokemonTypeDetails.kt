package com.shaiful.pokedex.data.remote.responses.pokemon_type_details

import com.shaiful.pokedex.screens.pokemon_list.models.PokemonListEntry

data class PokemonTypeDetails(
    val id: Int,
    val name: String,
    val pokemonList: List<PokemonListEntry>,
    val moveList: List<TypeMove>
)