package com.sandoval.marvelcharacters.data.remote.repository.marvel_character_detail

import com.sandoval.marvelcharacters.data.network.Failure
import com.sandoval.marvelcharacters.data.remote.api.MarvelCharactersService
import com.sandoval.marvelcharacters.data.utils.Either
import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DResult
import com.sandoval.marvelcharacters.domain.repository.marvel_character_detail.IGetMarvelCharacterDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataMarvelCharacterDetailRepository @Inject constructor(
    private val marvelCharactersService: MarvelCharactersService
) : IGetMarvelCharacterDetailRepository {
    override suspend fun getMarvelCharacterDetail(charactersId: Int): Flow<Either<Failure, List<DResult>>> =
        flow {
            val res = marvelCharactersService.getMarvelCharacterDetail(charactersId)
            //TODO Emit the result mapping the data through the domain
        }
}