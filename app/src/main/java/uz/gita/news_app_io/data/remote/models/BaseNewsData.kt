package uz.gita.news_app_io.data.remote.models

// Created by Jamshid Isoqov an 10/30/2022
data class BaseNewsData(
    val articles: List<NewsData>,
    val status: String,
    val totalResults: Int
)
