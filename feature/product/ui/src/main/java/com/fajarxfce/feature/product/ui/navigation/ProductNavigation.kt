package com.fajarxfce.feature.product.ui.navigation

import androidx.annotation.Keep
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fajarxfce.core.ui.navigation.Screen
import com.fajarxfce.feature.product.ui.ProductScreen
import com.fajarxfce.feature.product.ui.ProductViewModel
import kotlinx.serialization.Serializable
@Keep
@Serializable data object Product : Screen
@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.productGraph(

) {
    composable<Product> {
        val viewModel = hiltViewModel<ProductViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        val uiAction = viewModel::onAction
        ProductScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            uiAction = uiAction,
        )
    }
}