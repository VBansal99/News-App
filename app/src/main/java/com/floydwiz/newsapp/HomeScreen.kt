package com.floydwiz.newsapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun NewsHomeScreen(viewModel: NewsViewModel = viewModel(),navController: NavController) {
    val newsText by viewModel.newsText.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchNews("technology")
    }
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        Text(
//            text = "Home Screen",
//            color = Color.Black,
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold
//        )
//    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(newsText.split("\n")){articlesTitle ->
            Text(text = articlesTitle,
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier.padding(8.dp))
            Divider()
        }
    }
}