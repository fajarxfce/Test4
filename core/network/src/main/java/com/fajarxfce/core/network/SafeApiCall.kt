package com.fajarxfce.core.network

import com.fajarxfce.core.AuthorizationException
import com.fajarxfce.core.BadRequestException
import com.fajarxfce.core.NetworkException
import com.fajarxfce.core.NotFoundException
import com.fajarxfce.core.Resource
import com.fajarxfce.core.UnknownException
import com.fajarxfce.core.network.model.BaseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okio.IOException
import retrofit2.HttpException

suspend fun <T : Any> safeApiCall(apiToBeCalled: suspend () -> BaseResponse<T>): Resource<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response = apiToBeCalled()
            when (response.responseCode) {
                200 -> {
                    response.data?.let {
                        Resource.Success(it)
                    } ?: Resource.Error(UnknownException("Data is null"))
                }
                400 -> Resource.Error(BadRequestException(response.responseDesc.orEmpty()))
                401 -> Resource.Error(AuthorizationException(response.responseDesc.orEmpty()))
                404 -> Resource.Error(NotFoundException(response.responseDesc.orEmpty()))
                else -> Resource.Error(UnknownException(response.responseDesc.orEmpty()))
            }
        } catch (e: HttpException) {
            val message = Json.parseToJsonElement(
                e.response()?.errorBody()?.string().orEmpty()
            ).jsonObject["message"]?.jsonPrimitive?.content.orEmpty().ifEmpty {
                "An unknown error occurred, please try again later."
            }
            Resource.Error(UnknownException(message))
        } catch (e: IOException) {
            Resource.Error(NetworkException())
        } catch (e: Exception) {
            Resource.Error(UnknownException())
        }
    }
}