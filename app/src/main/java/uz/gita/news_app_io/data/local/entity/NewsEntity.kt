package uz.gita.news_app_io.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

// Created by Jamshid Isoqov an 10/30/2022
@Entity(tableName = "news_data")
@Parcelize
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val author: String?,
    val description: String?,
    val source: String?,
    val newsUrl: String,
    val imageUrl: String?,
    val time: String,
    val content: String?,
    val category: String,
    val saved: Int = 0
) : Parcelable