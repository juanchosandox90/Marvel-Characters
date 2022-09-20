package com.sandoval.marvelcharacters.ui.marvel_characters_list.fragments

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.sandoval.marvelcharacters.databinding.FragmentMarvelCharactersBinding
import com.sandoval.marvelcharacters.ui.base.BaseFragment
import com.sandoval.marvelcharacters.ui.marvel_characters_list.adapters.MarvelCharactersListAdapter
import com.sandoval.marvelcharacters.ui.marvel_characters_list.adapters.MarvelCharactersListItemListener
import com.sandoval.marvelcharacters.ui.marvel_characters_list.viewmodel.MarvelCharactersListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelCharactersListFragment : BaseFragment<FragmentMarvelCharactersBinding>(
    FragmentMarvelCharactersBinding::inflate
) {

    private val marvelCharactersListViewModel: MarvelCharactersListViewModel by viewModels()
    private lateinit var marvelCharactersListAdapter: MarvelCharactersListAdapter

    override fun initViews() {
        marvelCharactersListAdapter = MarvelCharactersListAdapter(
            onMarvelCharactersListItemSelected = MarvelCharactersListItemListener { characterId ->
                val action =
                    MarvelCharactersListFragmentDirections.actionNavigationMarvelCharacterListFragmentToMarvelCharacterDetailFragment(
                        characterId
                    )
                findNavController().navigate(action)
            }
        )
        marvelCharactersListAdapter.apply {
            registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                    binding.marvelCharactersListRecycler.smoothScrollToPosition(0)
                }
            })
        }
        setupMarvelCharactersListRecycler()
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
                    binding.marvelCharactersListRecycler.visibility = View.VISIBLE
                    hideLoading()
                }
                it.errorMessage != null -> {
                    setupGeneralErrorView()
                    hideLoading()
                }
            }
        }

        marvelCharactersListViewModel.listMarvelCharactersResults.observe(viewLifecycleOwner) { listMarvelCharacters ->
            marvelCharactersListAdapter.submitMarvelCharactersList(listMarvelCharacters)
        }
    }

    private fun setupMarvelCharactersListRecycler() {
        binding.marvelCharactersListRecycler.adapter = marvelCharactersListAdapter
    }

    private fun showLoading() {
        binding.loadingSpinner.loadingContainer.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.loadingSpinner.loadingContainer.visibility = View.GONE
    }

    private fun setupGeneralErrorView() {
        binding.marvelCharactersListRecycler.visibility = View.GONE
        binding.generalError.generalIssues.visibility = View.VISIBLE
        binding.generalError.reload.setOnClickListener {
            marvelCharactersListViewModel.getResults(100)
            binding.generalError.generalIssues.visibility = View.GONE
            showLoading()
        }
    }

    override fun showNoInternetConnection() {
        binding.marvelCharactersListRecycler.visibility = View.GONE
        binding.connectionError.generalIssues.visibility = View.VISIBLE
    }

}
