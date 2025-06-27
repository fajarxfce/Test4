package com.fajarxfce.feature.product.ui

import com.fajarxfce.core.domain.model.Product

object ProductContract {
    data class UiState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val products: List<Product> = emptyList(),
    )
    sealed interface UiEffect {
        data class ShowError(val message: String) : UiEffect
        data class OpenProductDetail(val productId: String) : UiEffect
    }
    sealed interface UiAction {
        data class OnProductClick(val productId: String) : UiAction
        data object LoadProducts : UiAction
        object OnRefresh : UiAction
        object OnRetry : UiAction
    }
}