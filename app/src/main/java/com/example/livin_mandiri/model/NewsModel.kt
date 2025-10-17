package com.example.livin_mandiri.model

data class NewsResponse(
    val articles: List<Article>
)

data class Article(
    val source: Source?,
    val title: String,
    val urlToImage: String?
)
{
    data class Source(
        val id: String?,
        val name: String?
    )
}