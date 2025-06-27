package com.fajarxfce.feature.login.ui


internal object LoginContract {
    data class UiState(
        val isLoading: Boolean = false,
        val email: String = "aci-android",
        val password: String = "akucintaindonesia",
        val isForgotPasswordSheetOpen: Boolean = false,
    )

    sealed interface UiAction{
        data object OnLoginClick : UiAction
    }

    sealed interface UiEffect {
        data object NavigateToHome : UiEffect
    }
}