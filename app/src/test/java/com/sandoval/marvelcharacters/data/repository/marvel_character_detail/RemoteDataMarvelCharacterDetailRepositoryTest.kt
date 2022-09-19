package com.sandoval.marvelcharacters.data.repository.marvel_character_detail

import com.google.common.truth.Truth
import com.sandoval.marvelcharacters.data.models.marvel_character_detail.MarvelCharacterDetailResponse
import com.sandoval.marvelcharacters.data.models.marvel_character_detail.*
import com.sandoval.marvelcharacters.data.models.marvel_character_detail.Result
import com.sandoval.marvelcharacters.data.network.Failure
import com.sandoval.marvelcharacters.data.remote.api.MarvelCharactersService
import com.sandoval.marvelcharacters.data.remote.repository.marvel_character_detail.RemoteDataMarvelCharacterDetailRepository
import com.sandoval.marvelcharacters.data.utils.Either
import com.sandoval.utils.UnitTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RemoteDataMarvelCharacterDetailRepositoryTest : UnitTest() {

    private lateinit var remoteDataMarvelCharacterDetailRepository: RemoteDataMarvelCharacterDetailRepository
    private lateinit var marvelCharacterDetailResponse: MarvelCharacterDetailResponse

    @MockK
    private lateinit var marvelCharactersService: MarvelCharactersService

    @MockK
    private lateinit var resultResponse: Response<MarvelCharacterDetailResponse>

    @Before
    fun setUp() {
        remoteDataMarvelCharacterDetailRepository =
            RemoteDataMarvelCharacterDetailRepository(marvelCharactersService)
    }

    @Test
    fun `Marvel character detail with null response body should return data error`() = runTest {
        every { resultResponse.body() } returns null
        every { resultResponse.isSuccessful } returns true
        coEvery { marvelCharactersService.getMarvelCharacterDetail(1234567890) } returns resultResponse
        val data = remoteDataMarvelCharacterDetailRepository.getMarvelCharacterDetail(1234567890)
        data.collect { a ->
            Truth.assertThat(a).isEqualTo(Either.Left(Failure.DataError))
        }
    }

    @Test
    fun `Marvel character detail should return server error when response is not successful`() =
        runTest {
            every { resultResponse.isSuccessful } returns false
            coEvery { marvelCharactersService.getMarvelCharacterDetail(1234567890) } returns resultResponse
            val data =
                remoteDataMarvelCharacterDetailRepository.getMarvelCharacterDetail(1234567890)
            data.collect { a ->
                Truth.assertThat(a).isEqualTo(Either.Left(Failure.ServerError))
            }
        }

    @Test
    fun `Marvel character detail should return data when response is successful`() = runTest {
        marvelCharacterDetailResponse = MarvelCharacterDetailResponse(
            attributionHTML = "", attributionText = "", code = 200, copyright = "", data = Data(
                offset = 0, limit = 10, total = 10, count = 10, results = listOf(
                    Result(
                        id = 1,
                        name = "3-D Man",
                        description = "description",
                        modified = "modified",
                        thumbnail = Thumbnail(
                            path = "thumbnail.png", extension = "png"
                        ),
                        resourceURI = "http://example.com/thumbnail.png",
                        comics = Comics(
                            available = 12,
                            collectionURI = "http://example.com/thumbnail.png",
                            items = listOf(
                                Item(
                                    resourceURI = "http://example.com/thumbnail.png",
                                    name = "Avengers"
                                )
                            ),
                            returned = 12
                        ),
                        series = Series(
                            available = 12,
                            collectionURI = "http://example.com/thumbnail.png",
                            items = listOf(
                                Item(
                                    resourceURI = "http://example.com/thumbnail.png",
                                    name = "Avengers"
                                )
                            ),
                            returned = 12
                        ),
                        stories = Stories(
                            available = 12,
                            collectionURI = "http://example.com/thumbnail.png",
                            items = listOf(
                                ItemXX(
                                    resourceURI = "http://example.com/thumbnail.png",
                                    name = "Avengers",
                                    type = "story"
                                )
                            ),
                            returned = 12
                        ),
                        events = Events(
                            available = 12,
                            collectionURI = "http://example.com/thumbnail.png",
                            items = listOf(
                                Item(
                                    resourceURI = "http://example.com/thumbnail.png",
                                    name = "Avengers"
                                )
                            ),
                            returned = 12
                        ),
                        urls = listOf(
                            Url(
                                type = "url",
                                url = "http://example.com/thumbnail.png",
                            )
                        ),
                    )
                )
            ), etag = "", status = "Ok"
        )
        every { resultResponse.body() } returns marvelCharacterDetailResponse
        every { resultResponse.isSuccessful } returns true
        coEvery { marvelCharactersService.getMarvelCharacterDetail(1234567890) } returns resultResponse
        val data = remoteDataMarvelCharacterDetailRepository.getMarvelCharacterDetail(1234567890)
        data.collect { a ->
            Truth.assertThat(a)
                .isEqualTo(marvelCharacterDetailResponse.data?.let { Either.Right(it.toDomainObject()) })
        }
    }
}