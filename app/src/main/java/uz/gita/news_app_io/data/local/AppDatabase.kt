package uz.gita.news_app_io.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.news_app_io.data.local.dao.NewsDao
import uz.gita.news_app_io.data.local.entity.NewsEntity

// Created by Jamshid Isoqov an 10/30/2022
@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){

    abstract fun newsDao():NewsDao

}