package com.fajarxfce.feature.product.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajarxfce.core.domain.usecase.GetProductsUseCase
import com.fajarxfce.core.onFailure
import com.fajarxfce.core.onSuccess
import com.fajarxfce.core.ui.delegate.mvi.MVI
import com.fajarxfce.core.ui.delegate.mvi.mvi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
) : ViewModel(),
    MVI<ProductContract.UiState, ProductContract.UiAction, ProductContract.UiEffect> by mvi(
        initialState = ProductContract.UiState(products = emptyList()),
    ) {
    init {
        onAction(ProductContract.UiAction.LoadProducts)
    }

    override fun onAction(action: ProductContract.UiAction) {
        when (action) {
            is ProductContract.UiAction.OnProductClick -> TODO()
            is ProductContract.UiAction.OnRefresh -> TODO()
            is ProductContract.UiAction.OnRetry -> TODO()
            is ProductContract.UiAction.LoadProducts -> { getProducts() }
        }
    }

    private fun getProducts() = viewModelScope.launch {
        updateUiState { copy(isLoading = true, errorMessage = null) }
        getProductsUseCase()
            .onSuccess {
                Timber.tag("ProductViewModel").d("getProducts: $it")
                updateUiState {
                    copy(
                        isLoading = false,
                        products = it
                    )
                }
            }
            .onFailure {
                Timber.tag("ProductViewModel").d("getProducts: $it")
                updateUiState { copy(isLoading = false, errorMessage = it.message) }
                emitUiEffect(ProductContract.UiEffect.ShowError(it.message ?: "Unknown error"))
            }
    }
}