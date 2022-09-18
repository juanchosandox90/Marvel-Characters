package com.sandoval.marvelcharacters.domain.models.marvel_character_detail

data class DStories(
    val available: Int?,
    val collectionURI: String?,
    val items: List<DItemXX>?,
    val returned: Int?
)