package com.sandoval.marvelcharacters.data.models.marvel_characters_list

import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DItemXXX

data class ItemXXX(
    val name: String?,
    val resourceURI: String?,
    val type: String?
) {
    fun toDomainObject() = DItemXXX(
        name = name ?: "",
        resourceURI = resourceURI ?: "",
        type = type ?: ""
    )
}