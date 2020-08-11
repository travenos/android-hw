package com.example.db_file.domain

interface DbFileInteractor {
    val currentItemText : String
    val currentItemNumber : Int
    val hasNextItem : Boolean
    val hasPreviousItem : Boolean

    fun goToNextItem() : String
    fun goToPreviousItem() : String
    fun removeCurrentItem() : String
    fun addItem(text : String) : String
}