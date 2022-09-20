package com.sandoval.marvelcharacters.ui.marvel_characters_list.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sandoval.marvelcharacters.data.network.Failure
import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DData
import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DResult
import com.sandoval.marvelcharacters.domain.usecase.marvel_characters_list.GetMarvelCharactersListUseCase
import com.sandoval.marvelcharacters.ui.mapper.toPresentation
import com.sandoval.marvelcharacters.ui.marvel_characters_list.models.state.MarvelCharactersListView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class MarvelCharactersListViewModel @Inject constructor(
    private val marvelCharactersListUseCase: GetMarvelCharactersListUseCase
) : ViewModel() {

    private val job = Job()

    private val _marvelCharactersListViewModel = MutableLiveData<MarvelCharactersListView>()
    val marvelCharactersListViewModel: LiveData<MarvelCharactersListView> get() = _marvelCharactersListViewModel

    private val _listMarvelCharactersResults = MutableLiveData<MutableList<DResult>>()
    val listMarvelCharactersResults: LiveData<MutableList<DResult>> get() = _listMarvelCharactersResults

    fun getResults(limit: Int) {
        _marvelCharactersListViewModel.value = MarvelCharactersListView(loading = true)
        marvelCharactersListUseCase(job, limit) {
            it.fold(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    private fun handleSuccess(data: DData) {
        if (data.results?.isEmpty() == true) {
            _marvelCharactersListViewModel.value = MarvelCharactersListView(loading = false)
            _marvelCharactersListViewModel.value = MarvelCharactersListView(isEmpty = true)
        } else {
            _marvelCharactersListViewModel.value = MarvelCharactersListView(loading = false)
            val presentation = data.toPresentation()
            _listMarvelCharactersResults.value = presentation.results?.toMutableList()
            _marvelCharactersListViewModel.value =
                MarvelCharactersListView(marvelCharactersList = presentation)
        }
    }

    private fun handleFailure(failure: Failure) {
        _marvelCharactersListViewModel.value = MarvelCharactersListView(loading = false)
        _marvelCharactersListViewModel.value =
            MarvelCharactersListView(errorMessage = failure.toString())
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}