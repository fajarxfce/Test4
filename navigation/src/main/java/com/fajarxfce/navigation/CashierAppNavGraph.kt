package com.fajarxfce.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.fajarxfce.feature.cart.ui.navigation.Cart
import com.fajarxfce.feature.cart.ui.navigation.cartScreen
import com.fajarxfce.feature.home.ui.navigation.Home
import com.fajarxfce.feature.home.ui.navigation.homeScreen
import com.fajarxfce.feature.login.ui.navigation.Login
import com.fajarxfce.feature.login.ui.navigation.loginScreen
import com.fajarxfce.feature.onboarding.ui.navigation.OnBoarding
import com.fajarxfce.feature.onboarding.ui.navigation.onBoardingScreen
import com.fajarxfce.feature.pos.ui.navigation.Pos
import com.fajarxfce.feature.pos.ui.navigation.posScreen
import com.fajarxfce.feature.splash.ui.navigation.Splash
import com.fajarxfce.feature.splash.ui.navigation.splashScreen
import com.fajarxfce.feature.transactionhistory.ui.navigation.TransactionHistoryRoute
import com.fajarxfce.feature.transactionhistory.ui.navigation.transactionHistoryScreen

@Composable
fun CashierAppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    onOpenDrawer: () -> Unit = {},
    onCloseDrawer: () -> Unit = {},
) {
    NavHost(
        navController = navController,
        startDestination = Splash,
        modifier = modifier,
        enterTransition = { slideIntoContainer(
            towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
            animationSpec = tween(700)
        ) },
    ) {
        loginScreen(
            onNavigateToHome = {
                navController.apply {
                    navigate(Home) {
                        popUpTo(Login) {
                            inclusive = true
                        }
                    }
                }
            },
            onNavigateBack = {
                navController.popBackStack()
            },
            onNavigateToRegister = {},
        )
    }
}