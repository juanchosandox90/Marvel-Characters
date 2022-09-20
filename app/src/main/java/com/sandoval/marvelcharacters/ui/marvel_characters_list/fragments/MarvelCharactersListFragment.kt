package com.sandoval.marvelcharacters.ui.marvel_characters_list.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sandoval.marvelcharacters.databinding.FragmentMarvelCharactersBinding
import com.sandoval.marvelcharacters.ui.base.BaseFragment
import com.sandoval.marvelcharacters.ui.marvel_characters_list.viewmodel.MarvelCharactersListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelCharactersListFragment : BaseFragment<FragmentMarvelCharactersBinding>(
    FragmentMarvelCharactersBinding::inflate
) {

    private val marvelCharactersListViewModel: MarvelCharactersListViewModel by viewModels()

    override fun initViews() {
        binding.goToDetail.setOnClickListener {
            val action =
                MarvelCharactersListFragmentDirections.actionNavigationMarvelCharacterListFragmentToMarvelCharacterDetailFragment()
            findNavController().navigate(action)
        }
    }

    override fun initViewModels() {
        marvelCharactersListViewModel.getResults(100)
        marvelCharactersListViewModel.marvelCharactersListViewModel.observe(viewLifecycleOwner) {
            when {
                it.loading -> {
                    showLoading()
                }
                it.isEmpty -> {
                    hideLoading()
                }
                it.marvelCharactersList != null -> {
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