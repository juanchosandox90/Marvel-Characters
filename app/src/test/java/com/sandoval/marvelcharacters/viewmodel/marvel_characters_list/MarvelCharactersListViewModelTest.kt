package com.sandoval.marvelcharacters.viewmodel.marvel_characters_list

import com.google.common.truth.Truth
import com.sandoval.marvelcharacters.data.network.Failure
import com.sandoval.marvelcharacters.data.utils.Either
import com.sandoval.marvelcharacters.domain.models.marvel_characters_list.*
import com.sandoval.marvelcharacters.domain.usecase.marvel_characters_list.GetMarvelCharactersListUseCase
import com.sandoval.marvelcharacters.ui.mapper.toPresentation
import com.sandoval.marvelcharacters.ui.marvel_characters_list.viewmodel.MarvelCharactersListViewModel
import com.sandoval.utils.UnitTest
import com.sandoval.utils.getOrAwaitValueTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class MarvelCharactersListViewModelTest : UnitTest() {

    @MockK
    private lateinit var getMarvelCharactersListUseCase: GetMarvelCharactersListUseCase

    private lateinit var marvelCharactersListViewModel: MarvelCharactersListViewModel

    private lateinit var data: DData

    @Before
    fun setUp() {
        data =
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

        marvelCharactersListViewModel =
            MarvelCharactersListViewModel(getMarvelCharactersListUseCase)
    }

    @Test
    fun `getMarvelCharactersList should return actual list`() {
        every { getMarvelCharactersListUseCase(any(), 100, any()) }.answers {
            lastArg<(Either<Failure, DData>) -> Unit>()(Either.Right(data))
        }
        marvelCharactersListViewModel.getResults(100)
        val res = marvelCharactersListViewModel.marvelCharactersListViewModel.getOrAwaitValueTest()
        Truth.assertThat(res.errorMessage).isNull()
        Truth.assertThat(res.loading).isFalse()
        Truth.assertThat(res.isEmpty).isFalse()
        Truth.assertThat(res.marvelCharactersList).isEqualTo(data.toPresentation())
    }

    @Test
    fun `getMarvelCharactersList should show error view when error occurs`() {
        every { getMarvelCharactersListUseCase(any(), 100, any()) }.answers {
            lastArg<(Either<Failure, List<DData>>) -> Unit>()(Either.Left(Failure.ServerError))
        }
        marvelCharactersListViewModel.getResults(100)
        val res = marvelCharactersListViewModel.marvelCharactersListViewModel.getOrAwaitValueTest()
        Truth.assertThat(res.errorMessage).isNotNull()
        Truth.assertThat(res.loading).isFalse()
        Truth.assertThat(res.isEmpty).isFalse()
        Truth.assertThat(res.marvelCharactersList).isNull()
    }

    @Test
    fun `getMarvelCharactersList should show error connection view when a error network connection occurs`() {
        every { getMarvelCharactersListUseCase(any(), 100, any()) }.answers {
            lastArg<(Either<Failure, List<DData>>) -> Unit>()(Either.Left(Failure.NetworkConnection))
        }
        marvelCharactersListViewModel.getResults(100)
        val res = marvelCharactersListViewModel.marvelCharactersListViewModel.getOrAwaitValueTest()
        Truth.assertThat(res.errorMessage).isNotNull()
        Truth.assertThat(res.loading).isFalse()
        Truth.assertThat(res.isEmpty).isFalse()
        Truth.assertThat(res.marvelCharactersList).isNull()
    }

}