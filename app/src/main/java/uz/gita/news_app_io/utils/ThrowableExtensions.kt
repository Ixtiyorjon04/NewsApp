package uz.gita.news_app_io.utils
// Created by Jamshid Isoqov an 10/12/2022


fun Throwable.getMessage() = this.message ?: "Unknown error"