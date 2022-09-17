package com.sandoval.marvelcharacters.ui.fragments.marvel_characters_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sandoval.marvelcharacters.databinding.FragmentMarvelCharactersBinding

class MarvelCharactersFragment : Fragment() {

    private var _fragmentMarvelCharactersBinding: FragmentMarvelCharactersBinding? = null
    private val fragmentMarvelCharactersBinding get() = _fragmentMarvelCharactersBinding!!

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

        return fragmentMarvelCharactersBinding.root
    }
}