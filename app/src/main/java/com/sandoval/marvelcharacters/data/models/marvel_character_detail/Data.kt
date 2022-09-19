package com.sandoval.marvelcharacters.data.models.marvel_character_detail

import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DData

data class Data(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    val results: List<Result>?,
    val total: Int?
){
    fun toDomainObject() = DData(
        count = count ?: 0,
        limit = limit ?: 0,
        offset = offset ?: 0,
        results = results?.map { it.toDomainObject() } ?: emptyList(),
        total = total ?: 0
    )
}