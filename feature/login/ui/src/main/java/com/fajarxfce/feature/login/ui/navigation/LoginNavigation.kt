package com.fajarxfce.feature.login.ui.navigation

import androidx.annotation.Keep
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fajarxfce.feature.login.ui.LoginScreen
import com.fajarxfce.feature.login.ui.LoginViewModel
import kotlinx.serialization.Serializable
@Keep
@Serializable data object Login

fun NavGraphBuilder.loginGraph(
    onNavigateToProduct: () -> Unit,
) {
    composable<Login> {
        val viewModel = hiltViewModel<LoginViewModel>()
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        val uiEffect = viewModel.uiEffect
        LoginScreen(
            uiState = uiState,
            uiEffect = uiEffect,
            onAction = viewModel::onAction,
            onNavigateToProduct = onNavigateToProduct,
        )
    }
}