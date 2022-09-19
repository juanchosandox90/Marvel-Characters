package com.sandoval.marvelcharacters.domain.models.marvel_characters_list

import com.sandoval.marvelcharacters.ui.marvel_characters_list.models.helper.IDResultList
import com.sandoval.marvelcharacters.ui.marvel_characters_list.models.helper.IThumbnail

data class DResult(
    val comics: DComics?,
    override val description: String?,
    val events: DEvents?,
    override val id: Int?,
    val modified: String?,
    override val name: String?,
    val resourceURI: String?,
    val series: DSeries?,
    val stories: DStories?,
    override val thumbnail: IThumbnail?,
    val urls: List<DUrl>?
) : IDResultList