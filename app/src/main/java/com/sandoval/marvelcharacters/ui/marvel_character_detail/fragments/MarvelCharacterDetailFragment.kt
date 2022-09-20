package com.sandoval.marvelcharacters.ui.marvel_character_detail.fragments

import android.view.View
import androidx.fragment.app.viewModels
import com.sandoval.marvelcharacters.databinding.FragmentMarvelCharacterDetailBinding
import com.sandoval.marvelcharacters.ui.base.BaseFragment
import com.sandoval.marvelcharacters.ui.marvel_character_detail.viewmodel.MarvelCharacterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelCharacterDetailFragment : BaseFragment<FragmentMarvelCharacterDetailBinding>(
    FragmentMarvelCharacterDetailBinding::inflate
) {

    private val marvelCharacterDetailViewModel: MarvelCharacterDetailViewModel by viewModels()

    override fun initViews() {
    }

    override fun initViewModels() {
        marvelCharacterDetailViewModel.getResults(1017100)//TODO Test ID, here the id will be passed via safeArgs
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
