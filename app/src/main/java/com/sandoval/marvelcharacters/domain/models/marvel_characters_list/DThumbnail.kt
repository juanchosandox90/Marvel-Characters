package com.sandoval.marvelcharacters.domain.models.marvel_characters_list

import com.sandoval.marvelcharacters.ui.marvel_characters_list.models.helper.IThumbnail

data class DThumbnail(
    override val extension: String?,
    override val path: String?
) : IThumbnail