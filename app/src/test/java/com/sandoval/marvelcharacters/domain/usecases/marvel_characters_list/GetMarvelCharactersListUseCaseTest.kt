package com.sandoval.marvelcharacters.domain.usecases.marvel_characters_list

import com.sandoval.marvelcharacters.data.utils.Either
import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.*
import com.sandoval.marvelcharacters.domain.repository.marvel_characters_list.IGetMarvelCharactersListRepository
import com.sandoval.marvelcharacters.domain.usecase.marvel_characters_list.GetMarvelCharactersListUseCase
import com.sandoval.utils.UnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetMarvelCharactersListUseCaseTest : UnitTest() {


    private lateinit var getMarvelCharactersListUseCase: GetMarvelCharactersListUseCase

    @MockK
    private lateinit var iGetMarvelCharactersListRepository: IGetMarvelCharactersListRepository

    @Before
    fun setUp() {
        getMarvelCharactersListUseCase =
            GetMarvelCharactersListUseCase(iGetMarvelCharactersListRepository)
    }

    @Test
    fun `should call getMarvelCharactersList from repository`() {
        runTest {
            coEvery {
                iGetMarvelCharactersListRepository.getMarvelCharactersList(100)
            } returns flow {
                emit(
                    Either.Right(
                        DData(
                            offset = 0, limit = 10, total = 10, count = 10, results = listOf(
                                DResult(
                                    id = 1,
                                    name = "3-D Man",
                                    description = "description",
                                    modified = "modified",
                                    thumbnail = DThumbnail(
                                        path = "thumbnail.png", extension = "png"
                                    ),
                                    resourceURI = "http://example.com/thumbnail.png",
                                    comics = DComics(
                                        available = 12,
                                        collectionURI = "http://example.com/thumbnail.png",
                                        items = listOf(
                                            DItem(
                                                resourceURI = "http://example.com/thumbnail.png",
                                                name = "Avengers"
                                            )
                                        ),
                                        returned = 12
                                    ),
                                    series = DSeries(
                                        available = 12,
                                        collectionURI = "http://example.com/thumbnail.png",
                                        items = listOf(
                                            DItem(
                                                resourceURI = "http://example.com/thumbnail.png",
                                                name = "Avengers"
                                            )
                                        ),
                                        returned = 12
                                    ),
                                    stories = DStories(
                                        available = 12,
                                        collectionURI = "http://example.com/thumbnail.png",
                                        items = listOf(
                                            DItemXXX(
                                                resourceURI = "http://example.com/thumbnail.png",
                                                name = "Avengers",
                                                type = "story"
                                            )
                                        ),
                                        returned = 12
                                    ),
                                    events = DEvents(
                                        available = 12,
                                        collectionURI = "http://example.com/thumbnail.png",
                                        items = listOf(
                                            DItem(
                                                resourceURI = "http://example.com/thumbnail.png",
                                                name = "Avengers"
                                            )
                                        ),
                                        returned = 12
                                    ),
                                    urls = listOf(
                                        DUrl(
                                            type = "url",
                                            url = "http://example.com/thumbnail.png",
                                        )
                                    ),
                                )
                            )
                        )
                    )
                )
            }
            getMarvelCharactersListUseCase.run(100)
            coVerify(exactly = 1) {
                iGetMarvelCharactersListRepository.getMarvelCharactersList(
                    100
                )
            }
        }
    }

}