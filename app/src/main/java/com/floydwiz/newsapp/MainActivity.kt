package com.floydwiz.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import com.floydwiz.newsapp.ui.theme.NewsAppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        var keepSplashScreen = true
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { keepSplashScreen }
        lifecycleScope.launch {
            delay(3000)
            keepSplashScreen = false
        }
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                MainScreen()
            }
        }
    }
}

