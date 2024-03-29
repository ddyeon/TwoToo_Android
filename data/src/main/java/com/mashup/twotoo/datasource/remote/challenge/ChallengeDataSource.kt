package com.mashup.twotoo.datasource.remote.challenge

import com.mashup.twotoo.datasource.remote.challenge.request.ApproveChallengeRequest
import com.mashup.twotoo.datasource.remote.challenge.request.ChallengeNoRequest
import com.mashup.twotoo.datasource.remote.challenge.request.CreateChallengeRequest
import com.mashup.twotoo.datasource.remote.challenge.response.Challenge
import com.mashup.twotoo.datasource.remote.challenge.response.ChallengeDetail
import util.NetworkResult
import javax.inject.Inject

class ChallengeDataSource @Inject constructor(
    private val challengeApi: ChallengeApi,
) {
    suspend fun createChallenge(
        createChallengeRequest: CreateChallengeRequest,
    ): NetworkResult<Challenge> {
        return challengeApi.createChallenge(createChallengeRequest = createChallengeRequest)
    }

    suspend fun getAllChallenge(): NetworkResult<List<Challenge>> {
        return challengeApi.getAllChallenge()
    }

    suspend fun getChallengeByNo(
        challengeNoRequest: ChallengeNoRequest,
    ): NetworkResult<ChallengeDetail> {
        return challengeApi.getChallengeByNo(challengeNo = challengeNoRequest.challengeNo)
    }

    suspend fun deleteChallengeByNo(
        challengeNoRequest: ChallengeNoRequest,
    ): NetworkResult<Int> {
        return challengeApi.deleteChallengeByNo(challengeNo = challengeNoRequest.challengeNo)
    }

    suspend fun approveChallengeWithNo(
        challengeNoRequest: ChallengeNoRequest,
        approveChallengeRequest: ApproveChallengeRequest,
    ): NetworkResult<Challenge> {
        return challengeApi.approveChallengeWithNo(
            challengeNo = challengeNoRequest.challengeNo,
            approveChallengeRequest = approveChallengeRequest,
        )
    }

    suspend fun finishChallengeWithNo(
        challengeNoRequest: ChallengeNoRequest,
    ): NetworkResult<Challenge> {
        return challengeApi.finishChallengeWithNo(
            challengeNo = challengeNoRequest.challengeNo,
        )
    }

    suspend fun getChallengeHistories(): NetworkResult<List<Challenge>> {
        return challengeApi.getChallengeHistories()
    }
}
