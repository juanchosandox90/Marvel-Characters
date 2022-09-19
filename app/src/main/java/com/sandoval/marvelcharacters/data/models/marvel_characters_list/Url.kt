package com.sandoval.marvelcharacters.data.models.marvel_characters_list

import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DUrl

data class Url(
    val type: String?,
    val url: String?
) {
    fun toDomainObject() = DUrl(
        type = type ?: "",
        url = url ?: ""
    )
}