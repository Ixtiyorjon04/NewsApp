package uz.gita.news_app_io.data.remote.models

import uz.gita.news_app_io.data.local.entity.NewsEntity

data class NewsData(
    val author: String?,
    val description: String?,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
) {
    fun toNewsEntity(category: String) = NewsEntity(
        0,
        title,
        author,
        description,
        source.name,
        url,
        urlToImage,
        publishedAt,
        content,
        category
    )
}