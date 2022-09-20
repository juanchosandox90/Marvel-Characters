package com.sandoval.marvelcharacters.domain.models.marvel_characters_list

data class DMarvelCharactersListResponse(
    val attributionHTML: String?,
    val attributionText: String?,
    val code: Int?,
    val copyright: String?,
    val data: DData?,
    val etag: String?,
    val status: String?
)