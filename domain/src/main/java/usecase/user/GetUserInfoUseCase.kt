package usecase.user

import model.user.UserInfoDomainModel
import repository.UserRepository
import util.NetworkResult
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): NetworkResult<UserInfoDomainModel> {
        return userRepository.getUserInfo()
    }
}
