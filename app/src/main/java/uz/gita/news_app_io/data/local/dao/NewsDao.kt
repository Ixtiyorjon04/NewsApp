package uz.gita.news_app_io.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.gita.news_app_io.data.local.entity.NewsEntity
import uz.gita.news_app_io.data.remote.models.NewsData

// Created by Jamshid Isoqov an 10/30/2022
@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(newsEntity: NewsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(list: List<NewsEntity>)

    @Update
    suspend fun updateNews(newsEntity: NewsEntity)

    @Query("SELECT*FROM news_data WHERE category=:category ORDER BY id DESC")
    fun getAllNewsByCategory(category: String): Flow<List<NewsEntity>>

    @Query("SELECT*FROM news_data WHERE saved==1")
    fun getAllSavedNews(): Flow<List<NewsEntity>>

    @Query("DELETE FROM news_data WHERE saved!=1")
    suspend fun deleteAllNews()

    @Query("DELETE FROM news_data WHERE saved==1")
    suspend fun deleteSavedNews()

    @Transaction
    suspend fun refreshData(list: List<NewsEntity>) {
        deleteAllNews()
        insertNews(list)
    }


}