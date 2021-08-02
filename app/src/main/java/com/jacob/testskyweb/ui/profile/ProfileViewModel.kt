package com.jacob.testskyweb.ui.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jacob.testskyweb.base.BaseViewModel
import com.jacob.testskyweb.models.WeatherModel
import com.jacob.testskyweb.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel
@Inject constructor(private val repository: Repository) :
    BaseViewModel() {

    private val _state = MutableLiveData<WeatherModel>()
    val state: LiveData<WeatherModel> = _state

    fun getWeathers(cityId: Int) {
        uiScope.launch {
            isLoading.value = true
            repository.getWeathers(cityId).let {
                if (it.isSuccessful) {
                    _state.postValue(it.body())
                    isLoading.value = false
                } else {
                    Log.e("TAG", "getWeathers:${it.message()} ")
                    isLoading.value = false
                }
            }
        }
    }
}