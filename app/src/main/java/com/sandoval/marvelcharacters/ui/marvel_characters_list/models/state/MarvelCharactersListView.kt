package com.sandoval.marvelcharacters.ui.marvel_characters_list.models.state

import com.sandoval.marvelcharacters.ui.marvel_characters_list.models.DMarvelCharactersListDataPresentation

data class MarvelCharactersListView(
    val loading: Boolean = false,
    val errorMessage: String? = null,
    val isEmpty: Boolean = false,
    val marvelCharactersList: DMarvelCharactersListDataPresentation? = null
)
