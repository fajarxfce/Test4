package com.fajarxfce.feature.login.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("job")
    val job: String? = null
)