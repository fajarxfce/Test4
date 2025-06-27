package com.fajarxfce.core.network.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

open class BaseResponse<T>(
    @SerializedName("data")
    val data: T? = null,
    @SerializedName("response_code")
    val responseCode: Int? = null,
    @SerializedName("response_desc")
    val responseDesc: String? = null,
)