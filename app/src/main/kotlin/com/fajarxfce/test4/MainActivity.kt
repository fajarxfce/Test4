package com.fajarxfce.test4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.fajarxfce.core.ui.theme.AppTheme
import com.fajarxfce.navigation.Test4AppNavGraph
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalMaterial3Api::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                val appState = rememberAppState()
                MainAppScreen(appState = appState)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreen(appState: AppState) {
    val scope = rememberCoroutineScope()

    Scaffold(
        containerColor = Color.White,
    ) { innerPadding ->
        Test4AppNavGraph(
            navController = appState.navController,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainAppScreenPreview() {
    val appState = rememberAppState()
    MainAppScreen(appState)
}