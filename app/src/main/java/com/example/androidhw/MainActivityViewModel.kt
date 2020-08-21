package com.example.androidhw

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.db_file.di.DaggerDbFileComponent
import com.example.db_file.di.DbFileModule
import com.example.db_file.domain.DbFileInteractor
import java.io.File
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(private val mContext : Context):
    ViewModel(), LifecycleObserver {

    private val mRepositoryInteractor : DbFileInteractor
    private val mCurrentText: MutableLiveData<String> = MutableLiveData()
    private val mHasNext: MutableLiveData<Boolean> = MutableLiveData()
    private val mHasPrevious: MutableLiveData<Boolean> = MutableLiveData()

    init {
        val dbFile = File(mContext.filesDir, FILE_NAME)
        val dbFileModule = DbFileModule(dbFile)
        val component = DaggerDbFileComponent.builder().dbFileModule(dbFileModule).build()
        mRepositoryInteractor = component.getDbFileInteractor()
        updateLiveData()
    }

    fun selectPrevious() {
        mRepositoryInteractor.goToPreviousItem()
        updateLiveData()
    }

    fun selectNext() {
        mRepositoryInteractor.goToNextItem()
        updateLiveData()
    }

    fun removeItem() {
        mRepositoryInteractor.removeCurrentItem()
        updateLiveData()
    }

    fun addItem(text : String) {
        mRepositoryInteractor.addItem(text)
        updateLiveData()
    }

    val currentText: LiveData<String> = mCurrentText
    val hasPreviousItem: LiveData<Boolean> = mHasPrevious
    val hasNextItem: LiveData<Boolean> = mHasNext

    private fun updateLiveData() {
        mCurrentText.value = mRepositoryInteractor.currentItemText
        mHasPrevious.value = mRepositoryInteractor.hasPreviousItem
        mHasNext.value = mRepositoryInteractor.hasNextItem
    }

    companion object {
        private const val FILE_NAME = "db_file.txt"
    }

}