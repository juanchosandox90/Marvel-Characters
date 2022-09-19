package com.sandoval.marvelcharacters.data.models.marvel_characters_list

import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DThumbnail

data class Thumbnail(
    val extension: String?,
    val path: String?
) {
    fun toDomainObject() = DThumbnail(
        extension = extension ?: "",
        path = path ?: ""
    )
}