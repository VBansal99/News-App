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
    private val _newsArticles = MutableStateFlow<List<Articles>>(emptyList())
    val newsArticles: StateFlow<List<Articles>> = _newsArticles

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    private val apiKey = BuildConfig.API_KEY

    fun fetchNews() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            try {
                val response =
                    RetrofitInstance.api.getNews(domains = "techcrunch.com,thenextweb.com", apiKey)
                val articles = response.articles
                //_newsText.value = articles.joinToString("\n") { it.title }
                _newsArticles.value = response.articles
            } catch (e: Exception) {
                _newsText.value = "Exception: ${e.message}"
                Log.e("NewsViewModel", "Exception: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}