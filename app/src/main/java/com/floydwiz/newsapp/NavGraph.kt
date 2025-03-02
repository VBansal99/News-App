package com.floydwiz.newsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            NewsHomeScreen(navController = navHostController)
        }
        composable(route = Screen.Search.route) {
            NewsSearchScreen()
        }
        composable(route = Screen.Saved.route) {
            NewsFavouriteScreen()
        }
        composable(
            route = "detail_screen?title={title}&image={image}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType;defaultValue="No title" },
                navArgument("image") { type = NavType.StringType;defaultValue="" })
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "No Title"
            val image = backStackEntry.arguments?.getString("image") ?: ""
            DetailScreen(title = title, image = image)
        }
    }
}