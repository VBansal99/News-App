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
            route = "detail_screen?title={title}&image={image}&description={description}&content={content}&publishedAt={publishedAt}&author={author}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType;defaultValue = "No title" },
                navArgument("image") { type = NavType.StringType;defaultValue = "" },
                navArgument("description") {
                    type = NavType.StringType;defaultValue = "No Description"
                },
                navArgument("content") { type = NavType.StringType;defaultValue = "No Content" },
                navArgument("publishedAt") {
                    type = NavType.StringType;defaultValue = "No Publish Date"
                },
                navArgument("author") { type = NavType.StringType;defaultValue = "No Author" }
            )
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: "No Title"
            val image = backStackEntry.arguments?.getString("image") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: "No Description"
            val content = backStackEntry.arguments?.getString("content") ?: "No Content"
            val publishedAt =
                backStackEntry.arguments?.getString("publishedAt") ?: "No Publish Date"
            val author = backStackEntry.arguments?.getString("author") ?: "No Author"
            DetailScreen(
                title = title,
                image = image,
                description = description,
                content = content,
                publishedAt = publishedAt,
                author = author
            )
        }
    }
}