package com.sandoval.marvelcharacters.domain.repository.marvel_character_detail

import com.sandoval.marvelcharacters.data.network.Failure
import com.sandoval.marvelcharacters.data.utils.Either
import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DData
import kotlinx.coroutines.flow.Flow

interface IGetMarvelCharacterDetailRepository {

    //Remote get marvel character detail Service
    suspend fun getMarvelCharacterDetail(charactersId: Int): Flow<Either<Failure, DData>>
}
