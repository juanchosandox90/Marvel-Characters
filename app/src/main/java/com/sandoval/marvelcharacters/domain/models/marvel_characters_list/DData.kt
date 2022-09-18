package com.sandoval.marvelcharacters.domain.models.marvel_characters_list

data class DData(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    val results: List<DResult>?,
    val total: Int?
)