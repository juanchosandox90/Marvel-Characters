package com.sandoval.marvelcharacters.data.models.marvel_character_detail

import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DUrl

data class Url(
    val type: String?,
    val url: String?
) {
    fun toDomainObject() = DUrl(
        type = type ?: "",
        url = url ?: ""
    )
}