package com.sandoval.marvelcharacters.ui.marvel_character_detail.models.helper

interface IDResultDetail {
    val description: String?
    val id: Int?
    val name: String?
    val thumbnail: IThumbnailDetail?
}

interface IThumbnailDetail {
    val extension: String?
    val path: String?
}