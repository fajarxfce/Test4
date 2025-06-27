package com.fajarxfce.feature.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fajarxfce.core.domain.usecase.LoginUseCase
import com.fajarxfce.core.onFailure
import com.fajarxfce.core.onSuccess
import com.fajarxfce.core.ui.delegate.mvi.MVI
import com.fajarxfce.core.ui.delegate.mvi.mvi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel(),
    MVI<LoginContract.UiState, LoginContract.UiAction, LoginContract.UiEffect> by mvi(initialState = LoginContract.UiState()) {
    override fun onAction(action: LoginContract.UiAction) {
        viewModelScope.launch {
            when (action) {
                is LoginContract.UiAction.OnLoginClick -> login()
                is LoginContract.UiAction.OnErrorDismissed -> {
                    updateUiState { copy(error = null) }
                }

                is LoginContract.UiAction.OnUsernameChanged -> updateUiState { copy(username = action.newUsername) }
                is LoginContract.UiAction.OnPasswordChanged -> updateUiState { copy(password = action.newPassword) }
            }
        }

    }

    private fun login() =
        viewModelScope.launch {
            updateUiState { copy(isLoading = true) }
            loginUseCase(
                username = currentUiState.username,
                password = currentUiState.password,
            )
                .onSuccess {
                    updateUiState {
                        copy(
                            isLoading = false,
                        )
                    }
                    emitUiEffect(LoginContract.UiEffect.NavigateToHome)
                }
                .onFailure {
                    updateUiState {
                        copy(
                            isLoading = false,
                            error = it.message.toString(),

                            )
                    }
                }
        }

}