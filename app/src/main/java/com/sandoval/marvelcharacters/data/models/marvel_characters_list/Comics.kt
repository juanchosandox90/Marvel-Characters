package com.sandoval.marvelcharacters.data.models.marvel_characters_list

data class Comics(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)