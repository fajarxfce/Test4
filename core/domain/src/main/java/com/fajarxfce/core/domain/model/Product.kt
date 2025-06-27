package com.fajarxfce.core.domain.model

data class Product(
    val productId: Int,
    val productName: String? = null,
    val productImage: String? = null,
    val productDesc: String? = null,
    val productPrice: Int? = null,
)