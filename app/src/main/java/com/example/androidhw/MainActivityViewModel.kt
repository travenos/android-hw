package com.example.androidhw

import android.content.Context
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.db_file.domain.DbFileInteractor
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(private val mContext : Context):
    ViewModel(), LifecycleObserver {
    //@Inject internal lateinit var mRepositoryInteractor : Lazy<DbFileInteractor>

    private val _currentText: MutableLiveData<String> = MutableLiveData()
    val currentText: LiveData<String> = _currentText

}