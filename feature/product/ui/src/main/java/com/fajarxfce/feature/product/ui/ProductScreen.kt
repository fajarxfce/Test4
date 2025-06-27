package com.fajarxfce.feature.product.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fajarxfce.core.ui.extension.collectWithLifecycle
import com.fajarxfce.feature.product.ui.component.ProductCard
import kotlinx.coroutines.flow.Flow

@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    uiState: ProductContract.UiState,
    uiEffect: Flow<ProductContract.UiEffect>,
    uiAction: (ProductContract.UiAction) -> Unit,
) {
    // Handle side effects
    uiEffect.collectWithLifecycle { effect ->
        when(effect) {
            is ProductContract.UiEffect.OpenProductDetail -> {}
            is ProductContract.UiEffect.ShowError -> {}
        }
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            uiState.errorMessage != null -> {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = uiState.errorMessage,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error,
                        textAlign = TextAlign.Center
                    )
                }
            }

            uiState.products.isEmpty() -> {
                Text(
                    text = "Tidak ada produk tersedia",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            else -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 180.dp),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(
                        items = uiState.products,
                        key = { product -> product.productId }
                    ) { product ->
                        ProductCard(
                            product = product,
                            onProductClick = { productId ->
                                uiAction(ProductContract.UiAction.OnProductClick(productId))
                            }
                        )
                    }
                }
            }
        }
    }
}