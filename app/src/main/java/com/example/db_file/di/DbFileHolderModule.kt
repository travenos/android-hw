package com.example.db_file.di

import com.example.db_file.data.impl.FileHolder
import dagger.Module
import dagger.Provides
import java.io.File

@Module
class DbFileHolderModule(file : File) {
    private val fileHolder = FileHolder(file)

    @PerDbFileModule
    @Provides
    fun provideDbFileHolder() = fileHolder
}