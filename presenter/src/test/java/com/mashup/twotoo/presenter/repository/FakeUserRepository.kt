package com.mashup.twotoo.presenter.repository

import model.user.PartnerInfoDomainModel
import model.user.UserAuthRequestDomainModel
import model.user.UserAuthResponseDomainModel
import model.user.UserInfoDomainModel
import model.user.UserNickNameDomainModel
import repository.UserRepository

class FakeUserRepository : UserRepository {
    override suspend fun userAuthorize(userAuthRequestDomainModel: UserAuthRequestDomainModel): Result<UserAuthResponseDomainModel> {
        TODO("Not yet implemented")
    }

    override suspend fun setUserNickName(userNickNameDomainModel: UserNickNameDomainModel): Result<UserInfoDomainModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getPartnerInfo(): Result<PartnerInfoDomainModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserInfo(): Result<UserInfoDomainModel> {
        return Result.success(
            UserInfoDomainModel(
                userNo = 1,
                nickname = "유저1",
                partnerNo = 2,
                partnerNickname = "유저2",
                totalChallengeCount = 1,
            ),
        )
    }

    override suspend fun deletePartner(): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun signOut(): Result<Boolean> {
        TODO("Not yet implemented")
    }
}
