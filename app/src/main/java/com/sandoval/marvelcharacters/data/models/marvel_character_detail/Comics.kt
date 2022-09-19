package com.sandoval.marvelcharacters.data.models.marvel_character_detail

import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DComics

data class Comics(
    val available: Int?,
    val collectionURI: String?,
    val items: List<Item>?,
    val returned: Int?
){
    fun toDomainObject() = DComics(
        available = available ?: 0,
        collectionURI = collectionURI ?: "",
        items = items?.map { it.toDomainObject() } ?: emptyList(),
        returned = returned ?: 0
    )
}