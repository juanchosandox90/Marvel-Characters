package com.sandoval.marvelcharacters.data.repository.marvel_characters_list

import com.google.common.truth.Truth
import com.sandoval.marvelcharacters.data.models.marvel_characters_list.*
import com.sandoval.marvelcharacters.data.network.Failure
import com.sandoval.marvelcharacters.data.remote.api.MarvelCharactersService
import com.sandoval.marvelcharacters.data.remote.repository.marvel_characters_list.RemoteDataMarvelCharactersListRepository
import com.sandoval.marvelcharacters.data.utils.Either
import com.sandoval.utils.UnitTest
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RemoteDataMarvelCharactersListRepositoryTest : UnitTest() {

    private lateinit var remoteDataMarvelCharactersListRepository: RemoteDataMarvelCharactersListRepository
    private lateinit var marvelCharactersListResponse: MarvelCharactersListResponse

    @MockK
    private lateinit var marvelCharactersService: MarvelCharactersService

    @MockK
    private lateinit var resultResponse: Response<MarvelCharactersListResponse>

    @Before
    fun setUp() {
        remoteDataMarvelCharactersListRepository =
            RemoteDataMarvelCharactersListRepository(marvelCharactersService)
    }

    @Test
    fun `Marvel characters list with null response body should return data error`() = runTest {
        every { resultResponse.body() } returns null
        every { resultResponse.isSuccessful } returns true
        coEvery { marvelCharactersService.getMarvelCharactersList(100) } returns resultResponse
        val data = remoteDataMarvelCharactersListRepository.getMarvelCharactersList(100)
        data.collect { a ->
            Truth.assertThat(a).isEqualTo(Either.Left(Failure.DataError))
        }
    }

    @Test
    fun `Marvel characters list should return server error when response is not successful`() =
        runTest {
            every { resultResponse.isSuccessful } returns false
            coEvery { marvelCharactersService.getMarvelCharactersList(100) } returns resultResponse
            val data = remoteDataMarvelCharactersListRepository.getMarvelCharactersList(100)
            data.collect { a ->
                Truth.assertThat(a).isEqualTo(Either.Left(Failure.ServerError))
            }
        }

    @Test
    fun `Marvel characters list should return data when response is successful`() = runTest {
        marvelCharactersListResponse = MarvelCharactersListResponse(
            attributionHTML = "",
            attributionText = "",
            code = 200,
            copyright = "",
            data = Data(
                offset = 0,
                limit = 10,
                total = 10,
                count = 10,
                results = listOf(
                    Result(
                        id = 1,
                        name = "3-D Man",
                        description = "description",
                        modified = "modified",
                        thumbnail = Thumbnail(
                            path = "thumbnail.png",
                            extension = "png"
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
                                ItemXXX(
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
            ),
            etag = "",
            status = "Ok"
        )
        every { resultResponse.body() } returns marvelCharactersListResponse
        every { resultResponse.isSuccessful } returns true
        coEvery { marvelCharactersService.getMarvelCharactersList(100) } returns resultResponse
        val data = remoteDataMarvelCharactersListRepository.getMarvelCharactersList(100)
        data.collect { a ->
            Truth.assertThat(a)
                .isEqualTo(marvelCharactersListResponse.data?.let { Either.Right(it.toDomainObject()) })
        }
    }
}