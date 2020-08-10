package com.example.androidhw.di

import com.example.androidhw.view.MainActivityView
import dagger.Component

@Component(modules = [MainActivityModule::class])
interface MainActivityComponent {

    fun inject(activity: MainActivityView)
}
