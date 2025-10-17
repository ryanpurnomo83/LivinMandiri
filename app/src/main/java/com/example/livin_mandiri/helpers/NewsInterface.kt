package com.example.livin_mandiri.helpers

import com.example.livin_mandiri.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {
    companion object {
        const val API_KEY = "b2fde4d87e38460a9d3aa717edc006cf"
    }

    @GET("v2/top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>

    @GET("v2/everything")
    fun getAllNews(
        @Query("q") q: String,
        @Query("apiKey") apiKey: String
    ):Call<NewsResponse>
    /*
    companion object{
        const val NEWS_API_KEY = "b2fde4d87e38460a9d3aa717edc006cf"
    }*/
}