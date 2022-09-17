package com.sandoval.marvelcharacters.data.models.marvel_character_detail

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)