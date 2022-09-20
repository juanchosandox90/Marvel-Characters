package com.sandoval.marvelcharacters.ui.marvel_character_detail.models.state

import com.sandoval.marvelcharacters.ui.marvel_character_detail.models.DMarvelCharacterDetailDataPresentation

data class MarvelCharacterDetailView(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val isEmpty: Boolean = false,
    val marvelCharacterDetail: DMarvelCharacterDetailDataPresentation? = null
)