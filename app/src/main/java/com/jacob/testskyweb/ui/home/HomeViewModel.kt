package com.jacob.testskyweb.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jacob.testskyweb.base.BaseViewModel
import com.jacob.testskyweb.models.FilmModel
import com.jacob.testskyweb.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject
constructor(private val repository: Repository) : BaseViewModel() {

    private val _state = MutableLiveData<MutableList<FilmModel>>()
    val state: LiveData<MutableList<FilmModel>> = _state

    fun getFilms(page: Int, limit: Int) {
        uiScope.launch {
            isLoading.value = true
            repository.getFilms(page, limit).let {
                if (it.isSuccessful) {
                    _state.postValue(it.body())
                    isLoading.value = false
                } else {
                    Log.e("TAG", "getFilms:${it.message()} ")
                    isLoading.value = false
                }
            }
        }
    }
}