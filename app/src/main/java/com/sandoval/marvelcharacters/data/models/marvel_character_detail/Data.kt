package com.sandoval.marvelcharacters.data.models.marvel_character_detail

data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    val results: List<Result>,
    val total: Int
)