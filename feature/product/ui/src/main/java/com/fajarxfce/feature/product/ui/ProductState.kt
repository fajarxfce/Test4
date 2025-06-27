package com.fajarxfce.feature.product.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
class ProductState(
    val scope: CoroutineScope,
    val bottomSheetState: SheetState,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberProductState(
    scope: CoroutineScope = rememberCoroutineScope(),
    bottomSheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
) = ProductState(
    scope = scope,
    bottomSheetState = bottomSheetState,
)
