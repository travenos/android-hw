package com.example.db_file.data


interface Repository<T> {
    fun add(item: T) : Boolean
    fun update(item: T) : Boolean
    fun remove(item: T) : Boolean
    fun query(specification: Specification): List<T>?

    interface Specification
}
