package com.example.db_file.di

import com.example.db_file.data.FileRepository
import com.example.db_file.data.impl.FileRepositoryImpl
import com.example.db_file.domain.DbFileInteractor
import com.example.db_file.domain.impl.DbFileInteractorImpl
import dagger.Binds
import dagger.Module

@Module(includes = [DbFileHolderModule::class])
interface DbFileModule {
    @PerDbFileModule
    @Binds
    fun provideDbFileInteractor(interactor : DbFileInteractorImpl) : DbFileInteractor

    @PerDbFileModule
    @Binds
    fun provideDbFileRepository(repository : FileRepositoryImpl) : FileRepository
}