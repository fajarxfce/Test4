package com.fajarxfce.feature.login.data.source.remote.request

data class LoginRequest(
    val username: String,
    val password: String,
    val via : String = "android",
)