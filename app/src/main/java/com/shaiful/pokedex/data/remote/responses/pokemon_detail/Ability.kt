package com.shaiful.pokedex.data.remote.responses.pokemon_detail

data class Ability(
    val ability: AbilityX,
    val is_hidden: Boolean,
    val slot: Int
)