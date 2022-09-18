package com.sandoval.marvelcharacters.domain.repository.marvel_characters_list

import com.sandoval.marvelcharacters.data.network.Failure
import com.sandoval.marvelcharacters.data.utils.Either
import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DResult
import kotlinx.coroutines.flow.Flow

interface IGetMarvelCharactersListRepository {

    //Remote get marvel character list Service
    suspend fun getMarvelCharactersList(limit: Int): Flow<Either<Failure, List<DResult>>>
}
