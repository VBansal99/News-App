package com.floydwiz.newsapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen(route = "home", title = "Home", icon = Icons.Default.Home)
    object Search : Screen(route = "search", title = "Search", icon = Icons.Default.Search)
    object Saved : Screen(
        route = "saved",
        title = "Saved",
        icon = Icons.Default.Favorite
    )
}