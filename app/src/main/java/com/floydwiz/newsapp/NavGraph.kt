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
            route = "detail_screen?title={title}&image={image}&description={description}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType;defaultValue="No title" },
                navArgument("image") { type = NavType.StringType;defaultValue="" },
                navArgument("description"){type = NavType.StringType;defaultValue = "No Description"}
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "No Title"
            val image = backStackEntry.arguments?.getString("image") ?: ""
            val description =backStackEntry.arguments?.getString("description") ?:"No Description"
            DetailScreen(title = title, image = image, description = description)
        }
    }
}