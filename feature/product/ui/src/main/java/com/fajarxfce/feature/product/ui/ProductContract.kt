package com.fajarxfce.feature.product.ui

import com.fajarxfce.core.domain.model.Product

object ProductContract {
    data class UiState(
        val isLoading: Boolean = false,
        val isBottomsheetOpen: Boolean = false,
        val errorMessage: String? = null,
        val products: List<Product> = emptyList(),
        val selectedProduct: Product? = null
    )
    sealed interface UiEffect {
        data class ShowError(val message: String) : UiEffect
        data object ShowProductDetail : UiEffect
    }
    sealed interface UiAction {
        data class OnProductClick(val productId: String) : UiAction
        data object LoadProducts : UiAction
        data object ShowBottomSheet : UiAction
        data object HideBottomsheet : UiAction
        data class OnShowProductDetail(val productId: Int) : UiAction
        object OnRefresh : UiAction
        object OnRetry : UiAction
    }
}