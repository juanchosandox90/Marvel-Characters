package com.sandoval.marvelcharacters.ui.marvel_characters_list.adapters

class MarvelCharactersListItemListener(
    val onMarvelCharactersListItemClickListener: (Int) -> Unit
) {
    fun onMarvelCharactersListItemClicked(characterId: Int) =
        onMarvelCharactersListItemClickListener(characterId)
}