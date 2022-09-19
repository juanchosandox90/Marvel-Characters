package com.sandoval.marvelcharacters.ui.marvel_characters_list.mapper

import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DData
import com.sandoval.marvelcharacters.ui.marvel_characters_list.models.DMarvelCharactersListDataPresentation

fun DData.toPresentation() = DMarvelCharactersListDataPresentation(
    count = count ?: 0,
    limit = limit ?: 0,
    offset = offset ?: 0,
    results = results,
    total = total ?: 0
)