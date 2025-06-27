package com.fajarxfce.core.domain.usecase

import com.fajarxfce.core.Resource
import com.fajarxfce.core.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
){
    suspend operator fun invoke(
        username: String,
        password: String
    ): Resource<Unit> {
        return loginRepository.loginWithUsernameAndPassword(username, password)
    }
}