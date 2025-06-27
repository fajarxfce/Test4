package com.fajarxfce.feature.pos.domain.repository

import androidx.paging.PagingData
import com.fajarxfce.feature.pos.domain.model.Category
import com.fajarxfce.feature.pos.domain.model.Merk
import com.fajarxfce.core.model.product.Product
import com.fajarxfce.feature.pos.domain.params.GetAllProductParams
import com.fajarxfce.feature.pos.domain.params.GetCategoryByQueryParams
import com.fajarxfce.feature.pos.domain.params.GetProductMerkByQueryParams
import kotlinx.coroutines.flow.Flow

interface PosRepository {
    fun getProducts(
        params: GetAllProductParams
    ): Flow<PagingData<Product>>
    fun getCategoryByQuery(
        params: GetCategoryByQueryParams
    ): Flow<PagingData<Category>>
    fun getProductMerkByQuery(
        params: GetProductMerkByQueryParams
    ): Flow<PagingData<Merk>>
}