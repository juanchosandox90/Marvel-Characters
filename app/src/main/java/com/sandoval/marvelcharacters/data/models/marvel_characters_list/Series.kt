package com.sandoval.marvelcharacters.data.models.marvel_characters_list

data class Series(
    val available: Int?,
    val collectionURI: String?,
    val items: List<Item>?,
    val returned: Int?
)