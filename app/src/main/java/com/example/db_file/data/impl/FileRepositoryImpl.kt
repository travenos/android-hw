package com.example.db_file.data.impl

import com.example.db_file.data.FileRepository
import com.example.db_file.data.FileRepositoryItem
import com.example.db_file.data.Repository
import com.example.db_file.data.TextFileSpecificationFactory
import java.io.*
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(private val file : File) : FileRepository {
    override val linesCount : Int
        get() {
            return try {
                var linesCount = 0
                FileReader(file).use { reader ->
                    reader.forEachLine { ++linesCount }
                }
                linesCount
            } catch (ignored : IOException) {
                0
            }
        }

    override val specificationFactory : TextFileSpecificationFactory
        get() = TextFileSpecificationFactoryImpl

    override fun add(item: FileRepositoryItem) : Boolean {
        var result = false
        if (item.id != null) {
            val foundItems = query(TextFileSpecificationFactoryImpl.getFileIdsSpecification(setOf(item.id)))
            if (foundItems?.isEmpty() == false) {
                return result
            }
        }
        try {
            FileWriter(file, true).use { writer ->
                writer.append(convertItemToLine(item))
                result = true
            }
        } catch (ignored : IOException) {
            result = false
        }
        return result
    }

    override fun update(item: FileRepositoryItem) : Boolean =
        update(item) {
                buffer, newItem ->
            buffer.append(convertItemToLine(newItem))
            buffer.append('\n')
        }

    override fun remove(item: FileRepositoryItem) : Boolean = update(item) { _, _ -> }

    override fun query(specification: Repository.Specification): List<FileRepositoryItem>? {
        return if (specification is FileRepository.TextFileSpecification) {
            val itemList = ArrayList<FileRepositoryItem>()
            var lineNumber = 0
            FileReader(file).use { reader ->
                reader.forEachLine { line ->
                    val item = convertLineToItem(line)
                    if (specification.acceptItem(item, lineNumber++)) {
                        itemList.add(item)
                    }
                }
            }
            itemList
        } else {
            null
        }
    }

    private inline fun update(item: FileRepositoryItem,
                              crossinline updateOp : (buffer : StringBuffer, item : FileRepositoryItem) -> Unit)
            : Boolean {
        var result = false
        item.id?.let {
            try {
                val readBuffer = StringBuffer()
                FileReader(file).use { reader ->
                    reader.forEachLine { line ->
                        if (convertLineToItem(line).id != item.id) {
                            readBuffer.append(line)
                            readBuffer.append('\n')
                        } else {
                            updateOp(readBuffer, item)
                            result = true
                        }
                    }
                }
                FileWriter(file).use { writer ->
                    writer.write(readBuffer.toString())
                }
            } catch (ignored : IOException) {
                result = false
            }
        }
        return result
    }

    private fun getNextFreeId() : Int {
        return try {
            var maxId = -1
            FileReader(file).use { reader ->
                reader.forEachLine { line ->
                    convertLineToItem(line).id?.let { id ->
                        if (id > maxId) {
                            maxId = id
                        }
                    }
                }
            }
            ++maxId
        } catch (ignored : IOException) {
            0
        }
    }

    private fun convertLineToItem(line : String) : FileRepositoryItem {
        //TODO!!! test with incorrect string
        val indexOfDelimiter = line.indexOf(':')
        val text : String = line.substring(indexOfDelimiter + 1)
        val id : Int? = try {
            line.substring(0, indexOfDelimiter).toInt()
        } catch (e : Exception) {
            when(e) {
                is NumberFormatException, is IndexOutOfBoundsException -> null
                else -> throw e
            }
        }
        return FileRepositoryItem(text, id)
    }

    private fun convertItemToLine(item : FileRepositoryItem) : String
            //TODO!!! test with null id, string with \n
            = item.id?.toString() ?: getNextFreeId().toString() + ":" + item.text.also { it.replace("\n", "\\n") }
}