package com.sandoval.marvelcharacters.ui.marvel_character_detail.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
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
        binding.marvelCharacterImageShare.setOnClickListener {
            setupShareFeature()
        }
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
                    setupGeneralErrorView()
                    hideLoading()
                }
            }
        }

    }

    private fun setupShareFeature() {
        val intent = Intent(Intent.ACTION_VIEW)
        val title = "Check this great app: Marvel Characters App"
        val chooser = Intent.createChooser(intent, title)
        try {
            startActivity(chooser)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                requireActivity(),
                "No App Found to share information",
                Toast.LENGTH_SHORT
            ).show()
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

    private fun setupGeneralErrorView() {
        binding.marvelCharacterDetailContainer.visibility = View.GONE
        binding.generalError.generalIssues.visibility = View.VISIBLE
        binding.generalError.reload.setOnClickListener {
            marvelCharacterDetailViewModel.getResults(characterId)
            binding.generalError.generalIssues.visibility = View.GONE
            showLoading()
        }
    }

    override fun showNoInternetConnection() {
        binding.marvelCharacterDetailContainer.visibility = View.GONE
        binding.connectionError.generalIssues.visibility = View.VISIBLE
    }
}
