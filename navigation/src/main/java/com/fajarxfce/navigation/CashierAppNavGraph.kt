package com.fajarxfce.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.fajarxfce.feature.login.ui.navigation.Login
import com.fajarxfce.feature.login.ui.navigation.loginScreen

@Composable
fun CashierAppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onOpenDrawer: () -> Unit = {},
    onCloseDrawer: () -> Unit = {},
) {
    NavHost(
        navController = navController,
        startDestination = Login,
        modifier = modifier,
        enterTransition = { slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
            animationSpec = tween(700)
        ) },
    ) {
        loginScreen(
            onNavigateToHome = {
                navController.apply {
                }
            },
        )
    }
}