package uz.gita.news_app_io.repository.impl

import kotlinx.coroutines.flow.Flow
import uz.gita.news_app_io.data.local.dao.NewsDao
import uz.gita.news_app_io.data.local.entity.NewsEntity
import uz.gita.news_app_io.data.remote.NewsApi
import uz.gita.news_app_io.repository.NewsRepository
import uz.gita.news_app_io.utils.ResultData
import uz.gita.news_app_io.utils.hasConnection
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi,
    private val dao: NewsDao
) : NewsRepository {

    override fun getAllNewsByCategory(category: String) = dao.getAllNewsByCategory(category)

    override suspend fun requestNewsByCategory(category: String): ResultData<Boolean> {
        if (hasConnection()) {
            try {
                val data = api.getNews(country = "in", category = category.lowercase())
                return if (data.isSuccessful) {
                    if (data.body() != null) {
                        val body = data.body()!!
                        dao.insertNews(body.articles.map { it.toNewsEntity(category) })
                        ResultData.Success(true)
                    } else {
                        ResultData.Error(Throwable("Body null"))
                    }
                } else {
                    ResultData.Error(Throwable(data.message()))
                }
            } catch (e: Exception) {
                return ResultData.Error(e)
            }
        } else {
            return ResultData.Message("No internet connection")
        }
    }

    override suspend fun updateNews(newsEntity: NewsEntity) {
        dao.updateNews(newsEntity)
    }

    override fun getAllSavedNews(): Flow<List<NewsEntity>> = dao.getAllSavedNews()
}