package com.sandoval.marvelcharacters.ui.marvel_characters_list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sandoval.marvelcharacters.databinding.ItemMarvelCharacterBinding
import com.sandoval.marvelcharacters.ui.marvel_characters_list.models.helper.IDResultList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MarvelCharactersListAdapter(
    private val onMarvelCharactersListItemSelected: MarvelCharactersListItemListener
) :
    ListAdapter<MarvelCharactersItems, RecyclerView.ViewHolder>(MarvelCharactersListDiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is MarvelCharactersItems.MarvelCharacterItem -> 0
            else -> throw ClassCastException("Unknown Type")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> MarvelCharactersListPreviewViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is MarvelCharactersListPreviewViewHolder -> {
                val marvelCharacter = item as MarvelCharactersItems.MarvelCharacterItem
                holder.bind(marvelCharacter.results, onMarvelCharactersListItemSelected)
            }
        }
    }

    fun submitMarvelCharactersList(list: List<IDResultList>) {
        adapterScope.launch {
            val items = list.map {
                MarvelCharactersItems.MarvelCharacterItem(it)
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
    }

    class MarvelCharactersListPreviewViewHolder private constructor(
        val binding: ItemMarvelCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            results: IDResultList,
            onMarvelCharactersListItemSelected: MarvelCharactersListItemListener
        ) {
            binding.results = results
            binding.resultsListener = onMarvelCharactersListItemSelected
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MarvelCharactersListPreviewViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMarvelCharacterBinding.inflate(layoutInflater, parent, false)
                return MarvelCharactersListPreviewViewHolder(binding)
            }
        }
    }

}

class MarvelCharactersListDiffCallback : DiffUtil.ItemCallback<MarvelCharactersItems>() {
    override fun areItemsTheSame(
        oldItem: MarvelCharactersItems, newItem: MarvelCharactersItems
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MarvelCharactersItems, newItem: MarvelCharactersItems
    ): Boolean {
        return oldItem == newItem
    }
}

sealed class MarvelCharactersItems {
    data class MarvelCharacterItem(val results: IDResultList) : MarvelCharactersItems() {
        override val id = results.id ?: 0
    }

    abstract val id: Int
}
