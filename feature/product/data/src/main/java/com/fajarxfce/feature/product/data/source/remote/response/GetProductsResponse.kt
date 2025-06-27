package com.fajarxfce.feature.product.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GetProductsResponse(

    @field:SerializedName("productDesc")
    val productDesc: String? = null,

    @field:SerializedName("productImage")
    val productImage: String? = null,

    @field:SerializedName("productId")
    val productId: Int? = null,

    @field:SerializedName("productName")
    val productName: String? = null,

    @field:SerializedName("productPrice")
    val productPrice: Int? = null
)