package com.sandoval.marvelcharacters.data.models.marvel_characters_list

import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DItem

data class Item(
    val name: String?,
    val resourceURI: String?
) {
    fun toDomainObject() = DItem(
        name = name ?: "",
        resourceURI = resourceURI ?: ""
    )
}