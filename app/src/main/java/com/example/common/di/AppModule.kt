package com.example.common.di

import android.content.Context
import com.example.common.ViewModelFactory

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@Module(includes = [ViewModelModule::class])
class AppModule(private val mContext : Context) {

    @Provides
    fun provideContext() = mContext
}