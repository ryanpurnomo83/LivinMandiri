package com.example.livin_mandiri.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class NewsResponse(
    val articles: List<Article>
)

data class Article(
    val source: Source?,
    val title: String,
    @SerializedName("publishedAt")
    val publishedAt: String?,
    val urlToImage: String?
)

{
    data class Source(
        val id: String?,
        val name: String?
    )
}