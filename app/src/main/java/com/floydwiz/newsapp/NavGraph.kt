package com.floydwiz.newsapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetupNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.Home.route) {
        composable(route = Screen.Home.route) {
            NewsHomeScreen(navController = navHostController)
        }
        composable(route = Screen.Search.route) {
            NewsSearchScreen()
        }
        composable(route = Screen.Saved.route){
            NewsFavouriteScreen()
        }
    }
}