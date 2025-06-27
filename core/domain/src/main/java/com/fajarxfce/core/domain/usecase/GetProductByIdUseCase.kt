package com.fajarxfce.core.domain.usecase

import com.fajarxfce.core.Resource
import com.fajarxfce.core.domain.model.Product
import com.fajarxfce.core.domain.repository.ProductRepository
import javax.inject.Inject

class GetProductByIdUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(productId: Int): Resource<Product> {
        return productRepository.getProductById(productId)
    }
}