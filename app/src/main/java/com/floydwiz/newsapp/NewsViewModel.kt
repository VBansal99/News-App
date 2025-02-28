package com.floydwiz.newsapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val _newsText = MutableStateFlow("Loading...")
    val newsText: StateFlow<String> = _newsText
    val apiKey=BuildConfig.API_KEY

    fun fetchNews(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    RetrofitInstance.api.getNews(query, apiKey)
                val articles = response.articles
                _newsText.value = articles.joinToString("\n") { it.title }
        } catch (e: Exception) {
            _newsText.value = "Exception: ${e.message}"
            Log.e("NewsViewModel", "Exception: ${e.message}")
        }
    }
}
}