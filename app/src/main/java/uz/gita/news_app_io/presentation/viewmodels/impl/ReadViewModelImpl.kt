package uz.gita.news_app_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.gita.news_app_io.data.local.entity.NewsEntity
import uz.gita.news_app_io.presentation.viewmodels.ReadViewModel
import uz.gita.news_app_io.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class ReadViewModelImpl @Inject constructor(
    private val repository: NewsRepository
) : ReadViewModel, ViewModel() {


    override fun updateNews(newsEntity: NewsEntity) {
        viewModelScope.launch {
            repository.updateNews(newsEntity)
        }
    }
}