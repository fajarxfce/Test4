package com.fajarxfce.feature.login.ui


internal object LoginContract {
    data class UiState(
        val isLoading: Boolean = false,
        val username: String = "aci-android",
        val password: String = "akucintaindonesia",
        val error: String? = null,
    )

    sealed interface UiAction{
        data object OnLoginClick : UiAction
        data object OnErrorDismissed : UiAction
        data class OnUsernameChanged(val newUsername: String) : UiAction
        data class OnPasswordChanged(val newPassword: String) : UiAction
    }

    sealed interface UiEffect {
        data object NavigateToHome : UiEffect
    }
}