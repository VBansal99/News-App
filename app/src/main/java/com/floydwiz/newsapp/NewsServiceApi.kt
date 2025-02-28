package com.floydwiz.newsapp

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServiceApi {
    @GET("everything")
   suspend fun getNews(@Query("domains") domains: String, @Query("apiKey") apiKey: String): News
}