package com.fajarxfce.feature.login.data.source

import com.fajarxfce.core.network.model.BaseResponse
import com.fajarxfce.feature.login.data.source.remote.request.LoginRequest
import com.fajarxfce.feature.login.data.source.remote.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

internal interface LoginApi {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): BaseResponse<LoginResponse>

}