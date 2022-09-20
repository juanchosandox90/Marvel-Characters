package com.sandoval.marvelcharacters.data.models.marvel_characters_list

import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DStories

data class Stories(
    val available: Int?,
    val collectionURI: String?,
    val items: List<ItemXXX>?,
    val returned: Int?
) {
    fun toDomainObject() = DStories(
        available = available ?: 0,
        collectionURI = collectionURI ?: "",
        items = items?.map { it.toDomainObject() } ?: emptyList(),
        returned = returned ?: 0
    )
}