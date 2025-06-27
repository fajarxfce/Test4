package com.fajarxfce.feature.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fajarxfce.core.ui.extension.collectWithLifecycle
import com.fajarxfce.core.ui.theme.AppTheme
import com.fajarxfce.core.ui.theme.CashierBlue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun LoginScreen(
    uiState: LoginContract.UiState,
    uiEffect: Flow<LoginContract.UiEffect>,
    onAction: (LoginContract.UiAction) -> Unit,
    onNavigateBack: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    uiEffect.collectWithLifecycle { effect ->
        when (effect) {
            is LoginContract.UiEffect.NavigateToHome -> onNavigateToHome()
            is LoginContract.UiEffect.NavigateToRegister -> onNavigateToRegister()
            is LoginContract.UiEffect.NavigateBack -> onNavigateBack()
        }
    }

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { onAction(LoginContract.UiAction.OnBackClick) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    actionIconContentColor = MaterialTheme.colorScheme.onBackground,
                ),
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.navigationBars,

        ) { paddingValues ->
        LoginContent(
            uiState = uiState,
            onEmailChange = { onAction(LoginContract.UiAction.OnEmailChange(it)) },
            onPasswordChange = { onAction(LoginContract.UiAction.OnPasswordChange(it)) },
            onLoginClick = { onAction(LoginContract.UiAction.OnLoginClick) },
            onForgotPasswordClick = { onAction(LoginContract.UiAction.OnForgotPasswordClick) },
            onRegisterClick = { onAction(LoginContract.UiAction.OnRegisterClick) },
            modifier = Modifier
                .padding(paddingValues),
        )
    }


    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                ) {},
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                color = CashierBlue,
                strokeCap = StrokeCap.Round,
            )
        }
    }
}

@Composable
internal fun LoginContent(
    modifier: Modifier,
    uiState: LoginContract.UiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onRegisterClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    AppTheme {
        LoginScreen(
            uiState = LoginContract.UiState(),
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateBack = {},
            onNavigateToRegister = {},
            onNavigateToHome = {},
        )
    }
}

@Preview
@Composable
private fun LoginScreenLoadingPreview() {
    AppTheme {
        LoginScreen(
            uiState = LoginContract.UiState(
                isLoading = true,
                email = "admin@email.com",
            ),
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateBack = {},
            onNavigateToRegister = {},
            onNavigateToHome = {},
        )
    }
}

@Preview
@Composable
private fun LoginScreenDialogPreview() {
    AppTheme {
        LoginScreen(
            uiState = LoginContract.UiState(
                email = "admin@email.com",
                isLoading = false,
            ),
            uiEffect = emptyFlow(),
            onAction = {},
            onNavigateBack = {},
            onNavigateToRegister = {},
            onNavigateToHome = {},
        )
    }
}