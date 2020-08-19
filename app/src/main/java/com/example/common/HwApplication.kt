package com.example.common

import android.app.Application
import android.content.Context
import com.example.common.di.AppModule


class HwApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        sMainAppModule = AppModule(this)
    }

    companion object {

        private lateinit var sMainAppModule : AppModule

        val mainAppModule get() = sMainAppModule
    }
}