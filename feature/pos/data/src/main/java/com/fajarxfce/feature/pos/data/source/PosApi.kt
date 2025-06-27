package com.fajarxfce.feature.pos.data.source

import com.fajarxfce.core.network.model.BaseResponse
import com.fajarxfce.feature.pos.data.model.GetAllProductResponse
import com.fajarxfce.feature.pos.data.model.GetCategoryByQueryResponse
import com.fajarxfce.feature.pos.data.model.GetProductMerkByQueryResponse
import com.fajarxfce.feature.pos.data.model.ProductDataItem
import com.fajarxfce.feature.pos.domain.params.GetCategoryByQueryParams
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.QueryMap

internal interface PosApi {
    @GET("product")
    suspend fun getProducts(
        @QueryMap(encoded = true) params: Map<String, String>
    ): BaseResponse<GetAllProductResponse>
    @GET("product-category")
    suspend fun getCateoryByQuery(
        @QueryMap(encoded = true) params: Map<String, String>
    ): BaseResponse<GetCategoryByQueryResponse>
    @GET("product-merk")
    suspend fun getProductMerkByQuery(
        @QueryMap(encoded = true) params: Map<String, String>
    ): BaseResponse<GetProductMerkByQueryResponse>
}