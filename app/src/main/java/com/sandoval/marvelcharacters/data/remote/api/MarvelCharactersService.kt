package com.sandoval.marvelcharacters.data.remote.api

import com.sandoval.marvelcharacters.commons.MARVEL_CHARACTERS_LIST_PATH
import com.sandoval.marvelcharacters.commons.MARVEL_CHARACTER_DETAIL_PATH
import com.sandoval.marvelcharacters.data.models.marvel_character_detail.MarvelCharacterDetailResponse
import com.sandoval.marvelcharacters.data.models.marvel_characters_list.MarvelCharactersListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelCharactersService {

    //Get Marvel Characters List
    @GET(MARVEL_CHARACTERS_LIST_PATH)
    suspend fun getMarvelCharactersList(): Response<MarvelCharactersListResponse>

    //Get Marvel Characters List
    @GET(MARVEL_CHARACTER_DETAIL_PATH)
    suspend fun getMarvelCharacterDetail(
        @Path("charactersId") charactersId: Int
    ): Response<MarvelCharacterDetailResponse>
}