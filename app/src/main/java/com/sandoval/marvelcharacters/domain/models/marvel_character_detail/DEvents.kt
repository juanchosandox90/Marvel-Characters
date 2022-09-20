package com.sandoval.marvelcharacters.domain.models.marvel_character_detail

data class DEvents(
    val available: Int?,
    val collectionURI: String?,
    val items: List<DItem>,
    val returned: Int?
)