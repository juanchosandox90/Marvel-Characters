package com.sandoval.marvelcharacters.viewmodel.marvel_character_detail

import com.google.common.truth.Truth
import com.sandoval.marvelcharacters.data.network.Failure
import com.sandoval.marvelcharacters.data.utils.Either
import com.sandoval.marvelcharacters.domain.models.marvel_character_detail.*
import com.sandoval.marvelcharacters.domain.usecase.marvel_character_detail.GetMarvelCharacterDetailUseCase
import com.sandoval.marvelcharacters.ui.mapper.toPresentation
import com.sandoval.marvelcharacters.ui.marvel_character_detail.viewmodel.MarvelCharacterDetailViewModel
import com.sandoval.utils.UnitTest
import com.sandoval.utils.getOrAwaitValueTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

class MarvelCharacterDetailViewModelTest : UnitTest() {

    @MockK
    private lateinit var getMarvelCharacterDetailUseCase: GetMarvelCharacterDetailUseCase

    private lateinit var marvelCharacterDetailViewModel: MarvelCharacterDetailViewModel

    private lateinit var data: DData

    @Before
    fun setUp() {
        data = DData(
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
                                resourceURI = "http://example.com/thumbnail.png", name = "Avengers"
                            )
                        ),
                        returned = 12
                    ),
                    series = DSeries(
                        available = 12,
                        collectionURI = "http://example.com/thumbnail.png",
                        items = listOf(
                            DItem(
                                resourceURI = "http://example.com/thumbnail.png", name = "Avengers"
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
                                resourceURI = "http://example.com/thumbnail.png", name = "Avengers"
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

        marvelCharacterDetailViewModel =
            MarvelCharacterDetailViewModel(getMarvelCharacterDetailUseCase)
    }

    @Test
    fun `getMarvelCharacterDetail should return actual character detail`() {
        every { getMarvelCharacterDetailUseCase(any(), 1234567890, any()) }.answers {
            lastArg<(Either<Failure, DData>) -> Unit>()(Either.Right(data))
        }
        marvelCharacterDetailViewModel.getResults(1234567890)
        val res =
            marvelCharacterDetailViewModel.marvelCharacterDetailViewModel.getOrAwaitValueTest()
        Truth.assertThat(res.errorMessage).isNull()
        Truth.assertThat(res.loading).isFalse()
        Truth.assertThat(res.isEmpty).isFalse()
        Truth.assertThat(res.marvelCharacterDetail).isEqualTo(data.toPresentation())
    }

    @Test
    fun `getMarvelCharacterDetail should show error view when error occurs`() {
        every { getMarvelCharacterDetailUseCase(any(), 1234567890, any()) }.answers {
            lastArg<(Either<Failure, DData>) -> Unit>()(Either.Left(Failure.ServerError))
        }
        marvelCharacterDetailViewModel.getResults(1234567890)
        val res =
            marvelCharacterDetailViewModel.marvelCharacterDetailViewModel.getOrAwaitValueTest()
        Truth.assertThat(res.errorMessage).isNotNull()
        Truth.assertThat(res.loading).isFalse()
        Truth.assertThat(res.isEmpty).isFalse()
        Truth.assertThat(res.marvelCharacterDetail).isNull()
    }

    @Test
    fun `getMarvelCharacterDetail should show error connection view when a error network connection occurs`() {
        every { getMarvelCharacterDetailUseCase(any(), 1234567890, any()) }.answers {
            lastArg<(Either<Failure, DData>) -> Unit>()(Either.Left(Failure.NetworkConnection))
        }
        marvelCharacterDetailViewModel.getResults(1234567890)
        val res =
            marvelCharacterDetailViewModel.marvelCharacterDetailViewModel.getOrAwaitValueTest()
        Truth.assertThat(res.errorMessage).isNotNull()
        Truth.assertThat(res.loading).isFalse()
        Truth.assertThat(res.isEmpty).isFalse()
        Truth.assertThat(res.marvelCharacterDetail).isNull()
    }
}