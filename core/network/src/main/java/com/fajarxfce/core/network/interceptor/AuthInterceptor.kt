package com.fajarxfce.core.network.interceptor

import com.fajarxfce.core.datastore.NiaPreferencesDataSource
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AuthInterceptor @Inject constructor(
    private val niaPreferencesDataSource: NiaPreferencesDataSource,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val token = runBlocking { niaPreferencesDataSource.getAuthToken() }

        val requestBuilder = originalRequest.newBuilder()
        token?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}