package com.sandoval.marvelcharacters.ui.mapper

import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DData
import com.sandoval.marvelcharacters.ui.marvel_character_detail.models.DMarvelCharacterDetailDataPresentation
import com.sandoval.marvelcharacters.ui.marvel_characters_list.models.DMarvelCharactersListDataPresentation

fun DData.toPresentation() = DMarvelCharactersListDataPresentation(
    count = count ?: 0,
    limit = limit ?: 0,
    offset = offset ?: 0,
    results = results,
    total = total ?: 0
)

fun com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DData.toPresentation() =
    DMarvelCharacterDetailDataPresentation(
        count = count ?: 0,
        limit = limit ?: 0,
        offset = offset ?: 0,
        results = results,
        total = total ?: 0
    )