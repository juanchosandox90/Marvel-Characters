package com.sandoval.marvelcharacters.data.network

sealed class Failure {

    object NetworkConnection : Failure()
    object DataError : Failure()
    object ServerError : Failure()

    data class FeatureFailure(
        val errorBody: String
    ) : Failure()
}