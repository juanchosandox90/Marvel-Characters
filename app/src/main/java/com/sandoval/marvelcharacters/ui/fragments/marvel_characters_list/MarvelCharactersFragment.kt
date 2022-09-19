package com.sandoval.marvelcharacters.ui.fragments.marvel_characters_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sandoval.marvelcharacters.databinding.FragmentMarvelCharactersBinding
import com.sandoval.marvelcharacters.ui.marvel_characters_list.viewmodel.MarvelCharactersListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelCharactersFragment : Fragment() {

    private var _fragmentMarvelCharactersBinding: FragmentMarvelCharactersBinding? = null
    private val fragmentMarvelCharactersBinding get() = _fragmentMarvelCharactersBinding!!
    private val marvelCharactersListViewModel: MarvelCharactersListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentMarvelCharactersBinding =
            FragmentMarvelCharactersBinding.inflate(inflater, container, false)

        fragmentMarvelCharactersBinding.goToDetail.setOnClickListener {
            val action =
                MarvelCharactersFragmentDirections.actionNavigationMarvelCharacterListFragmentToMarvelCharacterDetailFragment()
            findNavController().navigate(action)
        }

        initViewModels()

        return fragmentMarvelCharactersBinding.root
    }

    private fun initViewModels() {
        marvelCharactersListViewModel.getResults(100)
        marvelCharactersListViewModel.marvelCharactersListViewModel.observe(viewLifecycleOwner) {
            when {
                it.loading -> {
                    Log.d("Loading", "Loading...")
                }
                it.isEmpty -> {
                    Log.d("Data", "Empty...")
                }
                it.marvelCharactersList != null -> {
                    Log.d("MarvelCharactersList: ", it.marvelCharactersList.toString())
                }
                it.errorMessage != null -> {
                    Log.e("Error", it.errorMessage.toString())
                }
            }
        }
    }
}