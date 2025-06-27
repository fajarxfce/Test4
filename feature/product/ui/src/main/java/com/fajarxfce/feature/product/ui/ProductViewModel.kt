package com.fajarxfce.feature.product.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajarxfce.core.domain.usecase.GetProductByIdUseCase
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
    private val getProductByIdUseCase: GetProductByIdUseCase
) : ViewModel(),
    MVI<ProductContract.UiState, ProductContract.UiAction, ProductContract.UiEffect> by mvi(
        initialState = ProductContract.UiState(products = emptyList()),
    ) {
    init {
        onAction(ProductContract.UiAction.LoadProducts)
    }

    override fun onAction(action: ProductContract.UiAction) {
        when (action) {
            is ProductContract.UiAction.OnProductClick -> {}
            is ProductContract.UiAction.OnRefresh -> {}
            is ProductContract.UiAction.OnRetry -> {}
            is ProductContract.UiAction.LoadProducts -> { getProducts() }
            is ProductContract.UiAction.OnShowProductDetail -> {
                getProductById(action.productId)
            }
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
                updateUiState { copy(isLoading = false, errorMessage = it.message) }
            }
    }

    private fun getProductById(productId: Int) = viewModelScope.launch {
        getProductByIdUseCase(productId)
            .onSuccess {
                Timber.tag("ProductViewModel").d("getProductById: $it")
                updateUiState {
                    copy(
                        isLoading = false,
                        selectedProduct = it
                    )
                }
                emitUiEffect(ProductContract.UiEffect.ShowProductDetail)
            }
            .onFailure {
                updateUiState { copy(isLoading = false, errorMessage = it.message) }
                emitUiEffect(ProductContract.UiEffect.ShowError(it.message ?: "Unknown error"))
            }
    }
}