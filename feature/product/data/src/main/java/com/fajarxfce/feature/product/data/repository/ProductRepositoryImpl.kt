package com.fajarxfce.feature.product.data.repository

import com.fajarxfce.core.Resource
import com.fajarxfce.core.domain.model.Product
import com.fajarxfce.core.domain.repository.ProductRepository
import com.fajarxfce.core.map
import com.fajarxfce.core.network.safeApiCall
import com.fajarxfce.feature.product.data.source.remote.ProductApiService
import com.fajarxfce.feature.product.data.source.remote.response.GetProductsResponse
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApiService,
) : ProductRepository {
    override suspend fun getProducts(): Resource<List<Product>> {
        return safeApiCall { api.getProducts() }.map {
            it.toDomain()
        }
    }

    override suspend fun getProductById(productId: Int): Resource<Product> {
        return safeApiCall { api.getProductById(productId) }.map {
            it.toDomain()
        }
    }
}

fun List<GetProductsResponse?>?.toDomain(): List<Product> {
    return this?.map { dataItem ->
        Product(
            productId = dataItem?.productId ?: 0,
            productName = dataItem?.productName ?: "Unknown",
            productPrice = dataItem?.productPrice ?: 0,
            productDesc = dataItem?.productDesc ?: "No description",
            productImage = dataItem?.productImage,
        )
    } ?: emptyList()
}

fun GetProductsResponse?.toDomain(): Product {
    return Product(
        productId = this?.productId ?: 0,
        productName = this?.productName ?: "Unknown",
        productPrice = this?.productPrice ?: 0,
        productDesc = this?.productDesc ?: "No description",
        productImage = this?.productImage,
    )
}