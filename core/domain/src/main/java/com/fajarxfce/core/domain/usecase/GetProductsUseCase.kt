package com.fajarxfce.core.domain.usecase

import com.fajarxfce.core.Resource
import com.fajarxfce.core.domain.model.Product
import com.fajarxfce.core.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): Resource<List<Product>> {
        return productRepository.getProducts()
    }
}