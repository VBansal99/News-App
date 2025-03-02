package com.floydwiz.newsapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun DetailScreen(title: String, image: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LoadImage(image)
        LoadTitle(title)
    }
}

@Composable
private fun LoadImage(loadImage: String) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(loadImage).build(),
        contentDescription = "News Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
    )
}

@Composable
private fun LoadTitle(loadTitle: String) {
    Text(
        text = loadTitle,
        fontSize = 16.sp,
        fontStyle = FontStyle.Normal,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        maxLines = 2,
        minLines = 1,
        overflow = TextOverflow.Clip,
        modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp)
    )
}