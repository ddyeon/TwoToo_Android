package com.mashup.twotoo.datasource.remote.challenge

import com.mashup.twotoo.datasource.remote.challenge.request.ApproveChallengeRequest
import com.mashup.twotoo.datasource.remote.challenge.request.CreateChallengeRequest
import com.mashup.twotoo.datasource.remote.challenge.response.Challenge
import com.mashup.twotoo.datasource.remote.challenge.response.ChallengeDetail
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import util.NetworkResult

interface ChallengeApi {

    @POST("/challenge")
    suspend fun createChallenge(
        @Body createChallengeRequest: CreateChallengeRequest,
    ): NetworkResult<Challenge>

    @GET("/challenge")
    suspend fun getAllChallenge(): NetworkResult<List<Challenge>>

    @GET("/challenge/{challengeNo}")
    suspend fun getChallengeByNo(@Path("challengeNo") challengeNo: Int): NetworkResult<ChallengeDetail>

    @DELETE("/challenge/{challengeNo}")
    suspend fun deleteChallengeByNo(@Path("challengeNo") challengeNo: Int): NetworkResult<Int>

    @POST("/challenge/{challengeNo}/approve")
    suspend fun approveChallengeWithNo(
        @Path("challengeNo") challengeNo: Int,
        @Body approveChallengeRequest: ApproveChallengeRequest,
    ): NetworkResult<Challenge>

    @POST("/challenge/{challengeNo}/finish")
    suspend fun finishChallengeWithNo(
        @Path("challengeNo") challengeNo: Int,
    ): NetworkResult<Challenge>

    @GET("/challenge/histories")
    suspend fun getChallengeHistories(): NetworkResult<List<Challenge>>
}
