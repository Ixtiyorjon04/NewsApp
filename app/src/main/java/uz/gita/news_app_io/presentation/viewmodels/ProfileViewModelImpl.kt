package uz.gita.news_app_io.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.news_app_io.data.prefs.MySharedPref
import javax.inject.Inject

@HiltViewModel
class ProfileViewModelImpl @Inject constructor(
    private val sharedPreference: MySharedPref
) : ViewModel(), ProfileViewModel {

    override val nameLiveData: MutableLiveData<String> =
        MutableLiveData(sharedPreference.name)

    override val imageLiveData: MutableLiveData<String> =
        MutableLiveData(sharedPreference.image)

    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val changeNameLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val changeImageLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val aboutUsLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val contactLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val supportLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun changeName() {
        changeNameLiveData.postValue(Unit)
    }

    override fun changeImage() {
        changeImageLiveData.postValue(Unit)
    }

    override fun aboutClicked() {
        aboutUsLiveData.postValue(Unit)
    }

    override fun helpClicked() {
        contactLiveData.postValue(Unit)
    }

    override fun supportClicked() {
        supportLiveData.postValue(Unit)
    }

    override fun setName(name: String) {
        sharedPreference.name = name
        nameLiveData.postValue(name)
    }

    override fun setImage() {

    }

    override fun setImage(str: String) {
        sharedPreference.image = str
        imageLiveData.postValue(str)
    }

    override fun backClick() {
        backLiveData.postValue(Unit)
    }
}