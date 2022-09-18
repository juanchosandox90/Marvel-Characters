package com.sandoval.marvelcharacters.data.remote.repository.marvel_characters_list

import com.sandoval.marvelcharacters.data.network.Failure
import com.sandoval.marvelcharacters.data.remote.api.MarvelCharactersService
import com.sandoval.marvelcharacters.data.utils.Either
import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DResult
import com.sandoval.marvelcharacters.domain.repository.marvel_characters_list.IGetMarvelCharactersListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataMarvelCharactersListRepository @Inject constructor(
    private val marvelCharactersService: MarvelCharactersService
) : IGetMarvelCharactersListRepository {
    override suspend fun getMarvelCharactersList(limit: Int): Flow<Either<Failure, List<DResult>>> =
        flow {
            val res = marvelCharactersService.getMarvelCharactersList(limit)
            //TODO Emit the result mapping the data through the domain
        }
}
