package com.example.db_file.data

interface TextFileSpecificationFactory {
    fun getFileLinesSpecification(linesList : Set<Int>) : FileRepository.TextFileSpecification
    fun getFileIdsSpecification(idList : Set<Int>) : FileRepository.TextFileSpecification
}