package com.example.db_file.data.impl

import com.example.db_file.data.FileRepository
import com.example.db_file.data.FileRepositoryItem

class FileLinesSpecificationImpl(private val lineList : Set<Int>) : FileRepository.TextFileSpecification {
    override fun acceptItem(item : FileRepositoryItem, lineNumber : Int) : Boolean
            = lineNumber in lineList
}