package com.fajarxfce.core.domain.repository

import com.fajarxfce.core.Resource

interface LoginRepository {
    suspend fun loginWithUsernameAndPassword(
        username: String,
        password: String
    ): Resource<Unit>
}