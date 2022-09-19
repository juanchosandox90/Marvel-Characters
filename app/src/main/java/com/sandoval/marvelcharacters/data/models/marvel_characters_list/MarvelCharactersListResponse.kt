package com.sandoval.marvelcharacters.data.models.marvel_characters_list

import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DMarvelCharactersListResponse

data class MarvelCharactersListResponse(
    val attributionHTML: String?,
    val attributionText: String?,
    val code: Int?,
    val copyright: String?,
    val data: Data?,
    val etag: String?,
    val status: String?
) {
    fun toDomainObject() = DMarvelCharactersListResponse(
        attributionHTML = attributionHTML ?: "",
        attributionText = attributionText ?: "",
        code = code ?: 0,
        copyright = copyright ?: "",
        data = data?.toDomainObject(),
        etag = etag ?: "",
        status = status ?: ""
    )
}