package com.sandoval.marvelcharacters.ui.marvel_characters_list.bindingadapters

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.sandoval.marvelcharacters.R
import com.sandoval.marvelcharacters.ui.marvel_characters_list.models.helper.IThumbnail

object MarvelCharactersListBindingAdapter {

    @BindingAdapter(value = ["android:loadBackgroundImgOrColor"], requireAll = true)
    @JvmStatic
    fun loadImageFromUrl(
        imageView: ImageView, thumbnail: IThumbnail
    ) {
        val thumbnailPath = thumbnail.path
        val thumbnailExtension = thumbnail.extension

        when {
            thumbnailPath != null && thumbnailExtension != null -> {
                val thumbnailUrl = "$thumbnailPath.$thumbnailExtension"
                Log.d("ThumbnailUrl", thumbnailUrl)
                imageView.load(thumbnailUrl) {
                    crossfade(300)
                    listener(onError = { _, _ ->
                        imageView.setImageResource(R.drawable.ic_no_picture_256)
                    })
                }

            }
            else -> {
                imageView.setImageResource(R.drawable.ic_no_picture_256)
            }
        }
    }

}
