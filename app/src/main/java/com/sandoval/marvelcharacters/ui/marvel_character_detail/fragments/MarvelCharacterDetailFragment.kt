package com.sandoval.marvelcharacters.ui.marvel_character_detail.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sandoval.marvelcharacters.databinding.FragmentMarvelCharacterDetailBinding
import com.sandoval.marvelcharacters.ui.marvel_character_detail.viewmodel.MarvelCharacterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelCharacterDetailFragment : Fragment() {

    private var _fragmentMarvelCharacterDetailBinding: FragmentMarvelCharacterDetailBinding? = null
    private val fragmentMarvelCharacterDetailBinding get() = _fragmentMarvelCharacterDetailBinding!!
    private val marvelCharacterDetailViewModel: MarvelCharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentMarvelCharacterDetailBinding =
            FragmentMarvelCharacterDetailBinding.inflate(inflater, container, false)

        initViewModels()

        return fragmentMarvelCharacterDetailBinding.root
    }

    private fun initViewModels() {
        marvelCharacterDetailViewModel.getResults(1017100)//TODO Test ID, here the id will be passed via safeArgs
        marvelCharacterDetailViewModel.marvelCharacterDetailViewModel.observe(viewLifecycleOwner) {
            when {
                it.loading -> {
                    Log.d("Loading", "Loading...")
                }
                it.isEmpty -> {
                    Log.d("Data", "Empty...")
                }
                it.marvelCharacterDetail != null -> {
                    Log.d("MarvelDetailCharacter", it.marvelCharacterDetail.toString())
                }
                it.errorMessage != null -> {
                    Log.e("Error", it.errorMessage.toString())
                }
            }
        }
    }
}
