package com.example.db_file.data

interface FileRepository : Repository<FileRepositoryItem> {
    val linesCount : Int

    val specificationFactory : TextFileSpecificationFactory

    interface TextFileSpecification : Repository.Specification {
        fun acceptItem(item : FileRepositoryItem, lineNumber : Int) : Boolean
    }
}
