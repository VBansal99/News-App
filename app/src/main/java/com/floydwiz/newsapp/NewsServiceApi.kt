package com.floydwiz.newsapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsServiceApi {
    @GET("v2/everything")
   suspend fun getNews(@Query("q") keyword: String, @Query("apiKey") apiKey: String): News
}