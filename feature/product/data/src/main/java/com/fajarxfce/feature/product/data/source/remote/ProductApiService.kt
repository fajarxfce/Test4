package com.fajarxfce.feature.product.data.source.remote

import com.fajarxfce.core.network.model.BaseResponse
import com.fajarxfce.feature.product.data.source.remote.response.GetProductsResponse
import retrofit2.http.GET

interface ProductApiService {
    @GET("product")
    suspend fun getProducts(): BaseResponse<List<GetProductsResponse>>

    @GET("product/{id}")
    suspend fun getProductById(productId: Int): BaseResponse<GetProductsResponse>
}