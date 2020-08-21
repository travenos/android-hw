package com.example.db_file.di

import com.example.db_file.data.FileRepository
import com.example.db_file.domain.DbFileInteractor
import dagger.Component

@Component(modules = [DbFileModule::class])
@PerDbFileModule
interface DbFileComponent {
    fun getDbFileInteractor() : DbFileInteractor
    fun getDbFileRepository() : FileRepository
}
