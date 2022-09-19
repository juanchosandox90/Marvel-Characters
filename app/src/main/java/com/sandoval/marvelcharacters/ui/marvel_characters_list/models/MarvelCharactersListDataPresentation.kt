package com.sandoval.marvelcharacters.ui.marvel_characters_list.models

import android.os.Parcelable
import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DResult
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class DMarvelCharactersListDataPresentation(
    val count: Int?,
    val limit: Int?,
    val offset: Int?,
    val results: @RawValue List<DResult>?,
    val total: Int?
) : Parcelable