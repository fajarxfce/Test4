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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fajarxfce.core.domain.model.Product
import com.fajarxfce.core.ui.extension.collectWithLifecycle
import com.fajarxfce.feature.product.ui.component.ProductCard
import com.fajarxfce.feature.product.ui.component.ProductDetailBottomSheet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    modifier: Modifier = Modifier,
    uiState: ProductContract.UiState,
    uiEffect: Flow<ProductContract.UiEffect>,
    uiAction: (ProductContract.UiAction) -> Unit,
    productState: ProductState = rememberProductState(),
) {

    uiEffect.collectWithLifecycle { effect ->
        when (effect) {
            is ProductContract.UiEffect.ShowError -> {

            }

            is ProductContract.UiEffect.ShowProductDetail -> {
                uiAction(ProductContract.UiAction.ShowBottomSheet)
            }
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Product",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                },
            )
        },
    ) { innerPadding ->

        ProductContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            uiState = uiState,
            onProductClick = { productId ->
                uiAction(
                    ProductContract.UiAction.OnShowProductDetail(
                        productId,
                    ),
                )
            },
        )

    }

    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }

    if (uiState.errorMessage != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = uiState.errorMessage,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
            )
        }
    }

    if (uiState.products.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Tidak ada produk tersedia",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }

    if (uiState.isBottomsheetOpen && uiState.selectedProduct != null) {
        ProductDetailBottomSheet(
            product = uiState.selectedProduct,
            sheetState = productState.bottomSheetState,
            onDismiss = {
                productState.scope.launch {
                    productState.bottomSheetState.hide()
                }.invokeOnCompletion {
                    uiAction(ProductContract.UiAction.HideBottomsheet)
                }
            },
        )
    }
}

@Composable
fun ProductContent(
    modifier: Modifier = Modifier,
    uiState: ProductContract.UiState,
    onProductClick: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier,
    ) {
        items(
            items = uiState.products,
            key = { product -> product.productId },
        ) { product ->
            ProductCard(
                product = product,
                onProductClick = { productId ->
                    onProductClick(productId.toInt())
                },
            )
        }
    }
}

@Preview
@Composable
fun ProductScreenPreview() {
    ProductScreen(
        uiState = ProductContract.UiState(
            isLoading = true,
            products = listOf(
                Product(
                    productId = 1,
                    productName = "Produk 1",
                    productDesc = "Deskripsi Produk 1",
                    productPrice = 10000,
                    productImage = "https://via.placeholder.com/150",
                ),
            ),
        ),
        uiEffect = flowOf(),
        uiAction = {},
    )
}

@Preview
@Composable
fun ProductScreenWithBottomsheetPreview() {
    ProductScreen(
        uiState = ProductContract.UiState(
            selectedProduct = Product(
                productId = 1,
                productName = "Produk 1",
                productDesc = "Deskripsi Produk 1",
                productPrice = 10000,
                productImage = "https://via.placeholder.com/150",
            ),
            products = listOf(
                Product(
                    productId = 1,
                    productName = "Produk 1",
                    productDesc = "Deskripsi Produk 1",
                    productPrice = 10000,
                    productImage = "https://via.placeholder.com/150",
                ),
            ),
        ),
        uiEffect = flowOf(),
        uiAction = {},
    )
}
