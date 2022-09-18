package com.sandoval.marvelcharacters.domain.models.marvel_characters_list

data class DSeries(
    val available: Int?,
    val collectionURI: String?,
    val items: List<DItem>?,
    val returned: Int?
)