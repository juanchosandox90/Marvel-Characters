package com.sandoval.marvelcharacters.data.models.marvel_character_detail

import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DItemXX

data class ItemXX(
    val name: String?,
    val resourceURI: String?,
    val type: String?
){
    fun toDomainObject() = DItemXX(
        name = name ?: "",
        resourceURI = resourceURI ?: "",
        type = type ?: ""
    )
}