package com.sandoval.marvelcharacters.data.models.marvel_characters_list

import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DResult

data class Result(
    val comics: Comics?,
    val description: String?,
    val events: Events?,
    val id: Int?,
    val modified: String?,
    val name: String?,
    val resourceURI: String?,
    val series: Series?,
    val stories: Stories?,
    val thumbnail: Thumbnail?,
    val urls: List<Url>?
) {
    fun toDomainObject() = DResult(
        comics = comics?.toDomainObject(),
        description = description ?: "",
        events = events?.toDomainObject(),
        id = id ?: 0,
        modified = modified ?: "",
        name = name ?: "",
        resourceURI = resourceURI ?: "",
        series = series?.toDomainObject(),
        stories = stories?.toDomainObject(),
        thumbnail = thumbnail?.toDomainObject(),
        urls = urls?.map { it.toDomainObject() } ?: emptyList()
    )
}