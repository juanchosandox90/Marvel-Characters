package com.sandoval.marvelcharacters.data.remote.repository.marvel_character_detail

import com.sandoval.marvelcharacters.data.network.Failure
import com.sandoval.marvelcharacters.data.remote.api.MarvelCharactersService
import com.sandoval.marvelcharacters.data.utils.Either
import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DData
import com.sandoval.marvelcharacters.domain.repository.marvel_character_detail.IGetMarvelCharacterDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteDataMarvelCharacterDetailRepository @Inject constructor(
    private val marvelCharactersService: MarvelCharactersService
) : IGetMarvelCharacterDetailRepository {
    override suspend fun getMarvelCharacterDetail(charactersId: Int): Flow<Either<Failure, DData>> =
        flow {
            val res = marvelCharactersService.getMarvelCharacterDetail(charactersId)
            emit(
                when (res.isSuccessful) {
                    true -> {
                        res.body()?.let {
                            it.data?.let { it1 -> Either.Right(it1.toDomainObject()) }
                        } ?: Either.Left(Failure.DataError)
                    }
                    false -> Either.Left(Failure.ServerError)
                }
            )

        }
}