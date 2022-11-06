package uz.gita.news_app_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.gita.news_app_io.data.local.entity.NewsEntity
import uz.gita.news_app_io.presentation.viewmodels.SavedViewModel
import uz.gita.news_app_io.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class SavedViewModelImpl @Inject constructor(
    private val repository: NewsRepository
) : SavedViewModel ,ViewModel(){
    override val savedNewsFlow = MutableStateFlow<List<NewsEntity>>(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getAllSavedNews().collectLatest {
                savedNewsFlow.emit(it)
            }
        }
    }

}