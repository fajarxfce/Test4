package com.fajarxfce.feature.login.data.repository

import com.fajarxfce.core.Resource
import com.fajarxfce.core.UnknownException
import com.fajarxfce.core.domain.repository.LoginRepository
import com.fajarxfce.core.network.model.BaseResponse
import com.fajarxfce.core.network.safeApiCall
import com.fajarxfce.core.toUnit
import com.fajarxfce.feature.login.data.source.LoginApi
import com.fajarxfce.feature.login.data.source.remote.request.LoginRequest
import javax.inject.Inject

internal class LoginRepositoryImpl @Inject constructor(
    private val api: LoginApi,
) : LoginRepository{
    override suspend fun loginWithUsernameAndPassword(
        username: String,
        password: String,
    ): Resource<Unit> {
        val request = LoginRequest(
            username = username,
            password = password,
        )
        return safeApiCall { api.login(request) }.toUnit()
    }
}