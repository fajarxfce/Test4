package com.fajarxfce.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.fajarxfce.core.ui.R

internal val LocalIcons = staticCompositionLocalOf { CashierAppIcons() }

class CashierAppIcons {
    val arrowLeft: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_arrow_left)
    val check: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_check)

    val close: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_close)

    val lock: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_lock)

    val email: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_mail)

    val profileUnselected: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_profile)

    val search: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_search)

    val settings: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_settings)

    val starSelected: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_star_selected)

    val starUnselected: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_star_unselected)

    val trophy: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_trophy)

    val visibilityOn: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_visibility_on)

    val visibilityOff: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_visibility_off)

    val wrong: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_wrong)

    val starPattern: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_star_pattern)

    val google: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_google)

    val logo: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_logo)

    val happy: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_happy)

    val sad: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_sad)

    val smile: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_smile)

    val king: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_king)

    val sign: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_sign)

    val exit: ImageVector
        @Composable
        get() = ImageVector.vectorResource(id = R.drawable.core_ui_ic_exit)
}