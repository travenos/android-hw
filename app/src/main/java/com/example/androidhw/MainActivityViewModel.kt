package com.example.androidhw

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidhw.di.DaggerMainActivityComponent
import com.example.common.HwApplication
import com.example.common.di.AppModule
import com.example.db_file.di.DaggerDbFileComponent
import com.example.db_file.di.DbFileModule
import com.example.db_file.domain.DbFileInteractor
import java.io.File
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(private val mContext : Context):
    ViewModel(), LifecycleObserver {

    private val mRepositoryInteractor : DbFileInteractor
    private val _currentText: MutableLiveData<String> = MutableLiveData()

    init {
        Log.i("MyTRACE", "MainActivityViewModel: init") //TODO!!! Remove
        val dbFile = File(mContext.filesDir, FILE_NAME)
        val dbFileModule = DbFileModule(dbFile)
        val component = DaggerDbFileComponent.builder().dbFileModule(dbFileModule).build()
        mRepositoryInteractor = component.getDbFileInteractor()
        _currentText.value = mRepositoryInteractor.currentItemText
    }

    val currentText: LiveData<String> = _currentText

    companion object {
        private const val FILE_NAME = "db_file.txt"
    }

}