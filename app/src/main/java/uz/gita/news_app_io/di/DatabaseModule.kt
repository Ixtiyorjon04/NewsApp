package uz.gita.news_app_io.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.news_app_io.data.local.AppDatabase
import uz.gita.news_app_io.data.local.dao.NewsDao
import uz.gita.news_app_io.data.prefs.MySharedPref
import uz.gita.news_app_io.data.remote.NewsApi
import javax.inject.Singleton

// Created by Jamshid Isoqov an 10/30/2022
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val baseUrl = "https://newsapi.org/v2/"

    @[Provides Singleton]
    fun provideDatabase(
        @ApplicationContext ctx: Context
    ): AppDatabase = Room.databaseBuilder(ctx, AppDatabase::class.java, "news_data.db")
        .fallbackToDestructiveMigration()
        .build()

    @[Provides Singleton]
    fun provideNewsDao(appDatabase: AppDatabase):NewsDao = appDatabase.newsDao()

    @[Provides Singleton]
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @[Provides Singleton]
    fun provideNewsApi(retrofit: Retrofit): NewsApi = retrofit.create(NewsApi::class.java)

    @[Provides Singleton]
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("app_data", Context.MODE_PRIVATE)

    @[Provides Singleton]
    fun provideSharedPrefs(
        @ApplicationContext context: Context,
        sharedPreferences: SharedPreferences
    ): MySharedPref =
        MySharedPref(context, sharedPreferences)
}