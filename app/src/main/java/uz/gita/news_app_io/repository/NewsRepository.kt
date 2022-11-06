package uz.gita.news_app_io.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.news_app_io.data.enums.Category
import uz.gita.news_app_io.data.local.entity.NewsEntity
import uz.gita.news_app_io.utils.ResultData

// Created by Jamshid Isoqov an 10/30/2022
interface NewsRepository {

    fun getAllNewsByCategory(category: String): Flow<List<NewsEntity>>

    suspend fun requestNewsByCategory(category: String):ResultData<Boolean>

    suspend fun updateNews(newsEntity: NewsEntity)

    fun getAllSavedNews():Flow<List<NewsEntity>>


}