package uz.gita.news_app_io.presentation.viewmodels

import kotlinx.coroutines.flow.SharedFlow

// Created by Jamshid Isoqov an 10/30/2022
interface SplashViewModel {

  val navigator:SharedFlow<Unit>

}