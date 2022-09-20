package com.sandoval.marvelcharacters.domain.usecase.marvel_character_detail

import com.sandoval.marvelcharacters.data.network.Failure
import com.sandoval.marvelcharacters.data.utils.Either
import com.sandoval.marvelcharacters.domain.base.BaseUseCase
import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.DData
import com.sandoval.marvelcharacters.domain.repository.marvel_character_detail.IGetMarvelCharacterDetailRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMarvelCharacterDetailUseCase @Inject constructor(
    private val iGetMarvelCharacterDetailRepository: IGetMarvelCharacterDetailRepository
) : BaseUseCase<Int, DData>() {
    override suspend fun run(params: Int): Flow<Either<Failure, DData>> {
        return iGetMarvelCharacterDetailRepository.getMarvelCharacterDetail(params)
    }
}
