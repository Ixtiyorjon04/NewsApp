package uz.gita.news_app_io.presentation.viewmodels

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.news_app_io.data.local.entity.NewsEntity

// Created by Jamshid Isoqov an 10/30/2022
interface NewsViewModel {

    val loadingProgressBar:SharedFlow<Boolean>

    val messageSharedFlow:SharedFlow<String>

    val errorSharedFlow:SharedFlow<String>

    val newsSharedFlow: SharedFlow<List<NewsEntity>>

    fun getNewsByCategory(category: String)



}