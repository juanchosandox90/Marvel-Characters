package com.sandoval.marvelcharacters.domain.models.marvel_characters_list

data class DResult(
    val comics: DComics?,
    val description: String?,
    val events: DEvents?,
    val id: Int?,
    val modified: String?,
    val name: String?,
    val resourceURI: String?,
    val series: DSeries?,
    val stories: DStories?,
    val thumbnail: DThumbnail?,
    val urls: List<DUrl>?
)