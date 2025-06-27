package com.fajarxfce.feature.login.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fajarxfce.core.designsystem.component.button.Test4Button
import com.fajarxfce.core.designsystem.component.textfield.Test4PasswordTextField
import com.fajarxfce.core.designsystem.component.textfield.Test4TextField
import com.fajarxfce.core.designsystem.theme.AppTheme
import com.fajarxfce.core.ui.extension.collectWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun LoginScreen(
    uiState: LoginContract.UiState,
    uiEffect: Flow<LoginContract.UiEffect>,
    onAction: (LoginContract.UiAction) -> Unit,
    onNavigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
) {
    uiEffect.collectWithLifecycle { effect ->
        when (effect) {
            is LoginContract.UiEffect.NavigateToHome -> onNavigateToHome()
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
                    IconButton(onClick = {  }) {
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
            onLoginClick = { onAction(LoginContract.UiAction.OnLoginClick) },
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
                color = MaterialTheme.colorScheme.primary,
                strokeCap = StrokeCap.Round,
            )
        }
    }
}

@Composable
internal fun LoginContent(
    modifier: Modifier,
    uiState: LoginContract.UiState,
    onLoginClick: () -> Unit,
) {
    var username = uiState.email
    var password = uiState.password
    var usernameError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Welcome Back",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.feature_login_ui_sign_in_banner),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Email Field
        Test4TextField(
            value = username,
            onValueChange = {
                username = it
                usernameError = ""
            },
            label = stringResource(R.string.feature_login_ui_email),
            placeholder = stringResource(R.string.feature_login_ui_enter_your_email),
            isError = usernameError.isNotEmpty(),
            errorText = usernameError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) },
            ),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        Test4PasswordTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = ""
            },
            label = stringResource(R.string.feature_login_ui_password),
            placeholder = stringResource(R.string.feature_login_ui_enter_password),
            isError = passwordError.isNotEmpty(),
            errorText = passwordError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    if (validateInput(username, password)) {
                        onLoginClick()
                    }
                },
            ),
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Login Button
        Test4Button(
            text = stringResource(R.string.feature_login_ui_login),
            onClick = {
                focusManager.clearFocus()

                // Simple validation
                val isEmailValid =
                    username.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(username)
                        .matches()
                val isPasswordValid = password.isNotBlank() && password.length >= 6

                usernameError = if (!isEmailValid) {
                    if (username.isBlank()) "Email is required" else "Please enter a valid email"
                } else ""

                passwordError = if (!isPasswordValid) {
                    if (password.isBlank()) "Password is required" else "Password must be at least 6 characters"
                } else ""

                if (isEmailValid && isPasswordValid) {
                    onLoginClick()
                }
            },
            enabled = username.isNotBlank() && password.isNotBlank(),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
private fun validateInput(email: String, password: String): Boolean {
    return email.isNotBlank() &&
            android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
            password.isNotBlank() &&
            password.length >= 6
}
@Preview
@Composable
private fun LoginScreenPreview() {
    AppTheme {
        LoginScreen(
            uiState = LoginContract.UiState(),
            uiEffect = emptyFlow(),
            onAction = {},
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
            onNavigateToHome = {},
        )
    }
}