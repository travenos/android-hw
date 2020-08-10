package com.example.db_file.data.impl

import com.example.db_file.data.TextFileSpecificationFactory

object TextFileSpecificationFactoryImpl : TextFileSpecificationFactory {
    override fun getFileLinesSpecification(linesList : Set<Int>) = FileLinesSpecificationImpl(linesList)
    override fun getFileIdsSpecification(idList : Set<Int>) = FileIdsSpecification(idList)
}