package com.shaiful.pokedex.data.remote.responses.pokemon_detail

data class Move(
    val move: MoveX,
    val version_group_details: List<VersionGroupDetail>
)