package com.sandoval.marvelcharacters.domain.usecase.marvel_characters_list

import com.sandoval.marvelcharacters.data.network.Failure
import com.sandoval.marvelcharacters.data.utils.Either
import com.sandoval.marvelcharacters.domain.base.BaseUseCase
import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.DResult
import com.sandoval.marvelcharacters.domain.repository.marvel_characters_list.IGetMarvelCharactersListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMarvelCharactersListUseCase @Inject constructor(
    private val iGetMarvelCharactersListRepository: IGetMarvelCharactersListRepository
) : BaseUseCase<Int, List<DResult>>() {
    override suspend fun run(params: Int): Flow<Either<Failure, List<DResult>>> {
        return iGetMarvelCharactersListRepository.getMarvelCharactersList(params)
    }
}
