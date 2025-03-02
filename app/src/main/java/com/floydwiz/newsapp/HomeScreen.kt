package com.floydwiz.newsapp

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun NewsHomeScreen(viewModel: NewsViewModel = viewModel(), navController: NavController) {
    val articles by viewModel.newsArticles.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchNews()
    }
    val dataIsNotRefreshed = false
    if (!dataIsNotRefreshed) {
        PullDownToReload(articles, viewModel, navController)
    } else {
        ListNews(articles, navController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullDownToReload(
    articles: List<Articles>,
    viewModel: NewsViewModel,
    navController: NavController
) {
    val coroutineScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {
            coroutineScope.launch {
                isRefreshing = true
                viewModel.fetchNews()
                delay(3000)
                isRefreshing = false
            }
        },
    )
    {
        ListNews(articles, navController)
        Log.d("News Data", articles.toString())
    }
}


@Composable
private fun ListNews(articles: List<Articles>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        items(articles) { article ->
            NewsItem(article, navController = navController)
        }
    }
}

@Composable
private fun NewsItem(articles: Articles, navController: NavController) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(8.dp), onClick = {
            val encodedTitle = Uri.encode(articles.title)
            val encodedImage = Uri.encode(articles.urlToImage)
            val encodedDescription = Uri.encode(articles.description)
            val encodedContent = Uri.encode(articles.content)
            val encodedAuthor = Uri.parse(articles.author)
            val encodedPublishedAt = Uri.parse(articles.publishedAt)
            navController.navigate(route = "detail_screen?title=$encodedTitle&image=$encodedImage&description=$encodedDescription&content=$encodedContent&publishedAt=$encodedPublishedAt&author=$encodedAuthor")
        },
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(articles.urlToImage).build(),
                contentDescription = "News Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Text(
                text = articles.title,
                fontStyle = FontStyle.Normal,
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}