package uz.gita.news_app_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.news_app_io.data.local.entity.NewsEntity
import uz.gita.news_app_io.presentation.viewmodels.NewsViewModel
import uz.gita.news_app_io.repository.NewsRepository
import uz.gita.news_app_io.utils.getMessage
import javax.inject.Inject

@HiltViewModel
class NewsViewModelImpl @Inject constructor(
    private val repository: NewsRepository
) : NewsViewModel, ViewModel() {

    override val loadingProgressBar = MutableSharedFlow<Boolean>()

    override val messageSharedFlow = MutableSharedFlow<String>()

    override val errorSharedFlow = MutableSharedFlow<String>()

    override val newsSharedFlow = MutableSharedFlow<List<NewsEntity>>()

    override fun getNewsByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            loadingProgressBar.emit(true)
            repository.requestNewsByCategory(category)
                .onError {
                    errorSharedFlow.emit(it.getMessage())
                }
                .onMessage {
                    messageSharedFlow.emit(it)
                }
                .onSuccess {
                    loadingProgressBar.emit(false)
                    repository.getAllNewsByCategory(category).collectLatest {
                        newsSharedFlow.emit(it)
                    }
                }

        }
    }


}