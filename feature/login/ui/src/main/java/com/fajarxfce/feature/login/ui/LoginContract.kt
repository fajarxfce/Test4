package com.fajarxfce.feature.login.ui


internal object LoginContract {
    data class UiState(
        val isLoading: Boolean = false,
        val email: String = "admin@admin.com",
        val password: String = "password",
        val isForgotPasswordSheetOpen: Boolean = false,
    )

    sealed interface UiAction{
        data object OnBackClick : UiAction
        data class OnEmailChange(val email: String) : UiAction
        data class OnPasswordChange(val password: String) : UiAction
        data object OnLoginClick : UiAction
        data object OnRegisterClick : UiAction
        data object OnForgotPasswordClick : UiAction
        data object OnSendPasswordResetEmailClick : UiAction
        data object OnDialogDismiss : UiAction
        data object OnForgotPasswordSheetDismiss : UiAction
    }

    sealed interface UiEffect {
        data object NavigateBack : UiEffect
        data object NavigateToRegister : UiEffect
        data object NavigateToHome : UiEffect
    }
}