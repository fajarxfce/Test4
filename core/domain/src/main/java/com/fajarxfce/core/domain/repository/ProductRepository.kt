package com.fajarxfce.core.domain.repository

import com.fajarxfce.core.Resource
import com.fajarxfce.core.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): Resource<List<Product>>
    suspend fun getProductById(productId: Int): Resource<Product>
}