package com.fajarxfce.feature.product.ui.navigation

import androidx.annotation.Keep
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.fajarxfce.core.ui.navigation.Screen
import com.fajarxfce.feature.product.ui.ProductScreen
import kotlinx.serialization.Serializable
@Keep
@Serializable data object Product : Screen
fun NavGraphBuilder.productGraph(

) {
    composable<Product> {
        ProductScreen(

        )
    }
}