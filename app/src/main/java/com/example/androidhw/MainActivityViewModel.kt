package com.example.androidhw

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(): ViewModel(), LifecycleObserver {
    private val _currentText: MutableLiveData<String> = MutableLiveData()
    val currentText: LiveData<String> = _currentText
}