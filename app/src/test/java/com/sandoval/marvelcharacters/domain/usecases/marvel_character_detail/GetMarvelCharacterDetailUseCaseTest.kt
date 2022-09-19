package com.sandoval.marvelcharacters.domain.usecases.marvel_character_detail

import com.sandoval.marvelcharacters.data.utils.Either
import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.*
import com.sandoval.marvelcharacters.domain.repository.marvel_character_detail.IGetMarvelCharacterDetailRepository
import com.sandoval.marvelcharacters.domain.usecase.marvel_character_detail.GetMarvelCharacterDetailUseCase
import com.sandoval.utils.UnitTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetMarvelCharacterDetailUseCaseTest : UnitTest() {

    private lateinit var getMarvelCharacterDetailUseCase: GetMarvelCharacterDetailUseCase

    @MockK
    private lateinit var iGetMarvelCharacterDetailRepository: IGetMarvelCharacterDetailRepository

    @Before
    fun setUp() {
        getMarvelCharacterDetailUseCase =
            GetMarvelCharacterDetailUseCase(iGetMarvelCharacterDetailRepository)
    }

    @Test
    fun `should call getMarvelCharacterDetail from repository`() {
        runTest {
            coEvery {
                iGetMarvelCharacterDetailRepository.getMarvelCharacterDetail(1234567890)
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
                                            DItemXX(
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
            getMarvelCharacterDetailUseCase.run(1234567890)
            coVerify(exactly = 1) {
                iGetMarvelCharacterDetailRepository.getMarvelCharacterDetail(
                    1234567890
                )
            }
        }
    }
}