package com.sandoval.marvelcharacters.domain.models.marvel_character_detail

data class DData(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    val results: List<DResult>?,
    val total: Int?
)