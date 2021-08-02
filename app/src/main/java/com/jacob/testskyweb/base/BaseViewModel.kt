package com.jacob.testskyweb.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseViewModel() : ViewModel() {
    val job = Job()
    val uiScope = CoroutineScope(Dispatchers.Main.plus(job))
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
}