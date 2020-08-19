package com.example.db_file.di

import com.example.db_file.data.FileRepository
import com.example.db_file.data.impl.FileHolder
import com.example.db_file.data.impl.FileRepositoryImpl
import com.example.db_file.domain.DbFileInteractor
import com.example.db_file.domain.impl.DbFileInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import java.io.File

@Module
interface DbFileModuleBase {
    @PerDbFileModule
    @Binds
    fun provideDbFileInteractor(interactor : DbFileInteractorImpl) : DbFileInteractor

    @PerDbFileModule
    @Binds
    fun provideDbFileRepository(repository : FileRepositoryImpl) : FileRepository
}

@Module(includes = [DbFileModuleBase::class])
class DbFileModule(file : File) {
    private val fileHolder = FileHolder(file)

    @PerDbFileModule
    @Provides
    fun provideDbFileHolder() = fileHolder
}