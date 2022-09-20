package com.sandoval.marvelcharacters.data.models.marvel_character_detail

import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DThumbnail

data class Thumbnail(
    val extension: String?,
    val path: String?
){
    fun toDomainObject() =
        DThumbnail(
            extension = extension ?: "",
            path = path ?: ""
        )
}