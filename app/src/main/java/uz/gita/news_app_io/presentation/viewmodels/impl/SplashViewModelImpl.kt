package uz.gita.news_app_io.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.news_app_io.presentation.viewmodels.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor() : SplashViewModel, ViewModel() {

    override val navigator = MutableSharedFlow<Unit>()

    init {
        viewModelScope.launch {
            delay(1600)
            navigator.emit(Unit)
        }
    }
}