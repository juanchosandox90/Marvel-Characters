package com.sandoval.marvelcharacters.domain.models.marvel_character_detail

import com.sandoval.marvelcharacters.ui.marvel_character_detail.models.helper.IThumbnailDetail

data class DThumbnail(
    override val extension: String?,
    override val path: String?
) : IThumbnailDetail
