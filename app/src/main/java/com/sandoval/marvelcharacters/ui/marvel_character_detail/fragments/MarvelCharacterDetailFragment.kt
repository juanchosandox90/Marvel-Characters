package com.sandoval.marvelcharacters.ui.marvel_character_detail.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sandoval.marvelcharacters.databinding.FragmentMarvelCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarvelCharacterDetailFragment : Fragment() {

    private var _fragmentMarvelCharacterDetailBinding: FragmentMarvelCharacterDetailBinding? = null
    private val fragmentMarvelCharacterDetailBinding get() = _fragmentMarvelCharacterDetailBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentMarvelCharacterDetailBinding = FragmentMarvelCharacterDetailBinding.inflate(inflater, container, false)
        return fragmentMarvelCharacterDetailBinding.root
    }
}