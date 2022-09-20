package com.sandoval.marvelcharacters.domain.models.marvel_character_detail

import com.sandoval.marvelcharacters.ui.marvel_character_detail.models.helper.IDResultDetail
import com.sandoval.marvelcharacters.ui.marvel_character_detail.models.helper.IThumbnailDetail

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
    override val thumbnail: IThumbnailDetail?,
    val urls: List<DUrl>?
) : IDResultDetail