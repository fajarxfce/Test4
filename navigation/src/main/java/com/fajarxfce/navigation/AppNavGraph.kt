package com.fajarxfce.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.fajarxfce.feature.login.ui.navigation.Login
import com.fajarxfce.feature.login.ui.navigation.loginGraph
import com.fajarxfce.feature.product.ui.navigation.Product
import com.fajarxfce.feature.product.ui.navigation.productGraph

@Composable
fun Test4AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
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
        loginGraph(
            onNavigateToProduct = {
                navController.apply {
                    navigate(Product){
                        popUpTo(Login){
                            inclusive = true
                        }
                    }
                }
            },
        )
        productGraph(

        )
    }
}