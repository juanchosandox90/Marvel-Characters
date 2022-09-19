package com.sandoval.marvelcharacters.data.models.marvel_character_detail

import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DStories

data class Stories(
    val available: Int?,
    val collectionURI: String?,
    val items: List<ItemXX>?,
    val returned: Int?
){
    fun toDomainObject() = DStories(
        available = available ?: 0,
        collectionURI = collectionURI ?: "",
        items = items?.map { it.toDomainObject() } ?: emptyList(),
        returned = returned ?: 0
    )
}