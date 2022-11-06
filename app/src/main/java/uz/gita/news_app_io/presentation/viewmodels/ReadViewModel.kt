package uz.gita.news_app_io.presentation.viewmodels

import uz.gita.news_app_io.data.local.entity.NewsEntity

// Created by Jamshid Isoqov an 10/30/2022
interface ReadViewModel {

    fun updateNews(newsEntity: NewsEntity)

}