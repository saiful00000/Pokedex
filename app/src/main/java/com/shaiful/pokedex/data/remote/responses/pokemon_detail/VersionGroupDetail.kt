package com.shaiful.pokedex.data.remote.responses.pokemon_detail

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: MoveLearnMethod,
    val version_group: VersionGroup
)