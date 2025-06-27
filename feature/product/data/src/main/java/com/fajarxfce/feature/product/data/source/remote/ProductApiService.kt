package com.fajarxfce.feature.product.data.source.remote

import com.fajarxfce.core.network.model.BaseResponse
import com.fajarxfce.feature.product.data.source.remote.response.GetProductsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApiService {
    @GET("product")
    suspend fun getProducts(): BaseResponse<List<GetProductsResponse>>

    @GET("product")
    suspend fun getProductById(
        @Query("id") productId: Int
    ): BaseResponse<GetProductsResponse>
}