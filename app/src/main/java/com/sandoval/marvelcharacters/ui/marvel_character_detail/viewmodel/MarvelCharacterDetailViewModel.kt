package com.sandoval.marvelcharacters.ui.marvel_character_detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sandoval.marvelcharacters.data.network.Failure
import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DData
import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DResult
import com.sandoval.marvelcharacters.domain.usecase.marvel_character_detail.GetMarvelCharacterDetailUseCase
import com.sandoval.marvelcharacters.ui.mapper.toPresentation
import com.sandoval.marvelcharacters.ui.marvel_character_detail.models.state.MarvelCharacterDetailView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject

@HiltViewModel
class MarvelCharacterDetailViewModel @Inject constructor(
    private val marvelCharacterDetailUseCase: GetMarvelCharacterDetailUseCase
) : ViewModel() {

    private val job = Job()

    private val _marvelCharacterDetailViewModel = MutableLiveData<MarvelCharacterDetailView>()
    val marvelCharacterDetailViewModel: LiveData<MarvelCharacterDetailView> get() = _marvelCharacterDetailViewModel

    private val _detailMarvelCharactersResults = MutableLiveData<MutableList<DResult>>()
    val detailMarvelCharactersResults: LiveData<MutableList<DResult>> get() = _detailMarvelCharactersResults

    fun getResults(characterId: Int) {
        _marvelCharacterDetailViewModel.value = MarvelCharacterDetailView(loading = true)
        marvelCharacterDetailUseCase(job, characterId) {
            it.fold(
                ::handleFailure,
                ::handleSuccess
            )
        }
    }

    private fun handleSuccess(data: DData) {
        if (data.results?.isEmpty() == true) {
            _marvelCharacterDetailViewModel.value = MarvelCharacterDetailView(loading = false)
            _marvelCharacterDetailViewModel.value = MarvelCharacterDetailView(isEmpty = true)
        } else {
            _marvelCharacterDetailViewModel.value = MarvelCharacterDetailView(loading = false)
            val presentation = data.toPresentation()
            _detailMarvelCharactersResults.value = presentation.results?.toMutableList()
            _marvelCharacterDetailViewModel.value =
                MarvelCharacterDetailView(marvelCharacterDetail = presentation)
        }
    }

    private fun handleFailure(failure: Failure) {
        _marvelCharacterDetailViewModel.value = MarvelCharacterDetailView(loading = false)
        _marvelCharacterDetailViewModel.value =
            MarvelCharacterDetailView(errorMessage = failure.toString())
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}