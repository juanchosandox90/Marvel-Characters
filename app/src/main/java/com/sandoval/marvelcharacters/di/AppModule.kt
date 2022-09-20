package com.sandoval.marvelcharacters.di

import android.util.Log
import com.google.gson.GsonBuilder
import com.sandoval.marvelcharacters.BuildConfig
import com.sandoval.marvelcharacters.commons.*
import com.sandoval.marvelcharacters.data.remote.api.MarvelCharactersService
import com.sandoval.marvelcharacters.data.remote.repository.marvel_character_detail.RemoteDataMarvelCharacterDetailRepository
import com.sandoval.marvelcharacters.data.remote.repository.marvel_characters_list.RemoteDataMarvelCharactersListRepository
import com.sandoval.marvelcharacters.domain.repository.marvel_character_detail.IGetMarvelCharacterDetailRepository
import com.sandoval.marvelcharacters.domain.repository.marvel_characters_list.IGetMarvelCharactersListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        when {
            BuildConfig.DEBUG -> {
                return OkHttpClient().newBuilder().addInterceptor { chain ->
                        val currentTime = System.currentTimeMillis().toString()
                        val newUrl =
                            chain.request().url.newBuilder().addQueryParameter(TS, currentTime)
                                .addQueryParameter(APIKEY, PUBLIC_KEY).addQueryParameter(
                                    HASH, provideToMd5(currentTime + PRIVATE_KEY + PUBLIC_KEY)
                                ).build()
                        val newRequest = chain.request().newBuilder().url(newUrl).build()
                        chain.proceed(newRequest)
                    }.addInterceptor(logging).build()
            }
            else -> {
                return OkHttpClient().newBuilder().addInterceptor { chain ->
                        val currentTime = System.currentTimeMillis().toString()
                        val newUrl =
                            chain.request().url.newBuilder().addQueryParameter(TS, currentTime)
                                .addQueryParameter(APIKEY, PUBLIC_KEY).addQueryParameter(
                                    HASH, provideToMd5(currentTime + PRIVATE_KEY + PUBLIC_KEY)
                                ).build()
                        val newRequest = chain.request().newBuilder().url(newUrl).build()
                        chain.proceed(newRequest)
                    }.build()
            }
        }

    }

    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient, BASE_URL: String
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
        .baseUrl(BASE_URL).client(okHttpClient).build()

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): MarvelCharactersService =
        retrofit.create(MarvelCharactersService::class.java)

    @Provides
    @Singleton
    fun providesMarvelCharactersList(remoteDataMarvelCharactersListRepository: RemoteDataMarvelCharactersListRepository): IGetMarvelCharactersListRepository =
        remoteDataMarvelCharactersListRepository

    @Provides
    @Singleton
    fun providesMarvelCharacterDetail(remoteDataMarvelCharacterDetailRepository: RemoteDataMarvelCharacterDetailRepository): IGetMarvelCharacterDetailRepository =
        remoteDataMarvelCharacterDetailRepository

    fun provideToMd5(encrypted: String): String {
        var pass = encrypted
        var encryptedString: String? = null
        val md5: MessageDigest
        try {
            md5 = MessageDigest.getInstance("MD5")
            md5.update(pass.toByteArray(), 0, pass.length)
            pass = BigInteger(1, md5.digest()).toString(16)
            while (pass.length < 32) {
                pass = "0$pass"
            }
            encryptedString = pass
        } catch (e1: NoSuchAlgorithmException) {
            e1.printStackTrace()
        }
        Log.d("provideToMd5:", "hash -> $encryptedString")
        return encryptedString ?: ""
    }
}