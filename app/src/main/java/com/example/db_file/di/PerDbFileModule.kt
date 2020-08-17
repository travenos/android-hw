package com.example.db_file.di

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

@Scope
@Retention(RUNTIME)
internal annotation class PerDbFileModule