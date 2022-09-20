package com.sandoval.marvelcharacters.ui.marvel_character_detail.fragments

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.sandoval.marvelcharacters.R
import com.sandoval.marvelcharacters.databinding.FragmentMarvelCharacterDetailBinding
import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DResult
import com.sandoval.marvelcharacters.ui.base.BaseFragment
import com.sandoval.marvelcharacters.ui.marvel_character_detail.viewmodel.MarvelCharacterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelCharacterDetailFragment : BaseFragment<FragmentMarvelCharacterDetailBinding>(
    FragmentMarvelCharacterDetailBinding::inflate
) {

    private val marvelCharacterDetailViewModel: MarvelCharacterDetailViewModel by viewModels()
    private val args by navArgs<MarvelCharacterDetailFragmentArgs>()
    private var characterId: Int = 0

    override fun initViews() {
        characterId = args.characterId
    }

    override fun initViewModels() {
        marvelCharacterDetailViewModel.getResults(characterId)
        marvelCharacterDetailViewModel.marvelCharacterDetailViewModel.observe(viewLifecycleOwner) {
            when {
                it.loading -> {
                    showLoading()
                }
                it.isEmpty -> {
                    hideLoading()
                }
                it.marvelCharacterDetail != null -> {
                    when {
                        it.marvelCharacterDetail.results.isNullOrEmpty() -> {
                            Log.e("Marvel Character Detail", "No error found")
                        }
                        else -> {
                            setupMarvelCharacterDetailView(it.marvelCharacterDetail.results)
                        }
                    }

                    hideLoading()
                }
                it.errorMessage != null -> {
                    hideLoading()
                }
            }
        }

    }

    private fun setupMarvelCharacterDetailView(results: List<DResult>) {
        binding.marvelCharacterNameDetail.text = results.first().name.toString()
        binding.marvelCharacterDescriptionDetail.text = results.first().description.toString()
        val thumbnailPath = results.first().thumbnail?.path
        val thumbnailExtension = results.first().thumbnail?.extension

        when {
            thumbnailPath != null && thumbnailExtension != null -> {
                val thumbnailUrl = "$thumbnailPath.$thumbnailExtension"
                Log.d("ThumbnailUrl", thumbnailUrl)
                binding.marvelCharacterImageDetail.load(thumbnailUrl) {
                    crossfade(300)
                    listener(onError = { _, _ ->
                        binding.marvelCharacterImageDetail.setImageResource(R.drawable.ic_no_picture_256)
                    })
                }

            }
            else -> {
                binding.marvelCharacterImageDetail.setImageResource(R.drawable.ic_no_picture_256)
            }
        }
    }

    private fun showLoading() {
        binding.loadingSpinner.loadingContainer.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loadingSpinner.loadingContainer.visibility = View.GONE
    }
}
