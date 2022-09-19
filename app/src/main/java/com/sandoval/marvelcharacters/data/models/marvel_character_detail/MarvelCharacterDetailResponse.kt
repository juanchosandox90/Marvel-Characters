package com.sandoval.marvelcharacters.data.models.marvel_character_detail

import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DMarvelCharacterDetailResponse

data class MarvelCharacterDetailResponse(
    val attributionHTML: String?,
    val attributionText: String?,
    val code: Int?,
    val copyright: String?,
    val data: Data?,
    val etag: String?,
    val status: String?
){
    fun toDomainObject() = DMarvelCharacterDetailResponse(
            attributionHTML = attributionHTML ?: "",
            attributionText = attributionText ?: "",
            code = code ?: 0,
            copyright = copyright ?: "",
            data = data?.toDomainObject(),
            etag = etag ?: "",
            status = status ?: ""
        )
}