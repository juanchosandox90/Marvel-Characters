package com.sandoval.marvelcharacters.data.models.marvel_characters_list

import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DComics

data class Comics(
    val available: Int?,
    val collectionURI: String?,
    val items: List<Item>?,
    val returned: Int?
) {
    fun toDomainObject() = DComics(
        available = available ?: 0,
        collectionURI = collectionURI ?: "",
        items = items?.map { it.toDomainObject() } ?: emptyList(),
        returned = returned ?: 0
    )
}