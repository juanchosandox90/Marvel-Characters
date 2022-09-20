package com.sandoval.marvelcharacters.ui.marvel_character_detail.models

import android.os.Parcelable
import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DResult
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class DMarvelCharacterDetailDataPresentation(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    val results: @RawValue List<DResult>?,
    val total: Int?
) : Parcelable
