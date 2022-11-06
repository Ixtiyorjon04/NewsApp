package uz.gita.news_app_io.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.news_app_io.repository.NewsRepository
import uz.gita.news_app_io.repository.impl.NewsRepositoryImpl
import javax.inject.Singleton

// Created by Jamshid Isoqov an 10/30/2022
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindsRepository(impl: NewsRepositoryImpl): NewsRepository

}