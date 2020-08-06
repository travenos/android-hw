package com.example.androidhw.di

import com.example.androidhw.MainActivityViewModel
import com.example.common.di.ViewModelKey

import androidx.lifecycle.ViewModel
import com.example.common.di.AppModule

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [AppModule::class])
interface MainActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindViewModel(viewModel: MainActivityViewModel): ViewModel
}