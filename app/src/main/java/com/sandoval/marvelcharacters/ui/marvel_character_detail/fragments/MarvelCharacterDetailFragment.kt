package com.sandoval.marvelcharacters.ui.marvel_character_detail.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sandoval.marvelcharacters.databinding.FragmentMarvelCharacterDetailBinding
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
                    hideLoading()
                }
                it.errorMessage != null -> {
                    hideLoading()
                }
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
