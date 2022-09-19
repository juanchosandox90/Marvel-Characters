package com.sandoval.marvelcharacters.data.models.marvel_character_detail

import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DItem

data class Item(
    val name: String?,
    val resourceURI: String?
){
    fun toDomainObject() = DItem(
        name = name ?: "",
        resourceURI = resourceURI ?: ""
    )
}