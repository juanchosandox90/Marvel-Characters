package com.sandoval.marvelcharacters.domain.models.marvel_characters_list

data class DStories(
    val available: Int?,
    val collectionURI: String?,
    val items: List<DItemXXX>?,
    val returned: Int?
)