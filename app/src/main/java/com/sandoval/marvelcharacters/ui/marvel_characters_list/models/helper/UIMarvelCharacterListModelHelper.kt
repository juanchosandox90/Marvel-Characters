package com.sandoval.marvelcharacters.ui.marvel_characters_list.models.helper

interface IDResultList {
    val description: String?
    val id: Int?
    val name: String?
    val thumbnail: IThumbnail?
}

interface IThumbnail {
    val extension: String?
    val path: String?
}