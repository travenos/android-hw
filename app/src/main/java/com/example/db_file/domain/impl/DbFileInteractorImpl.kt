package com.example.db_file.domain.impl

import com.example.db_file.data.FileRepository
import com.example.db_file.data.FileRepositoryItem
import com.example.db_file.domain.DbFileInteractor
import javax.inject.Inject


class DbFileInteractorImpl constructor(private val mRepository : FileRepository,
                                       private var mCurrentLine : Int) : DbFileInteractor {
    private var mCurrentCachedItem : FileRepositoryItem? = null
    private val mSpecificationFactory = mRepository.specificationFactory

    @Inject constructor(repository : FileRepository) : this(repository, 0)

    private val currentItem : FileRepositoryItem
        get() {
            if (mCurrentCachedItem == null) {
                val itemList = getItemList(mCurrentLine)
                if (itemList?.isEmpty() == false) {
                    mCurrentCachedItem = itemList[0]
                }
            }
            return mCurrentCachedItem ?: FileRepositoryItem("")
        }

    override val currentItemText : String
        get() = currentItem.text

    override val currentItemNumber : Int
        get() = mCurrentLine

    override val hasPreviousItem : Boolean
        get() = mCurrentLine > 0

    override val hasNextItem : Boolean
        get() = mCurrentLine < mRepository.linesCount - 1

    override fun goToNextItem() : String {
        val itemList = getItemList(mCurrentLine + 1)
        if (itemList?.isEmpty() == false) {
            ++mCurrentLine
            mCurrentCachedItem = itemList[0]
            return itemList[0].text
        }
        return currentItemText
    }

    override fun goToPreviousItem() : String {
        val itemList = getItemList(mCurrentLine - 1)
        if (itemList?.isEmpty() == false) {
            --mCurrentLine
            mCurrentCachedItem = itemList[0]
            return itemList[0].text
        }
        return currentItemText
    }

    override fun removeCurrentItem() : String {
        if (currentItem.id != null) {
            if (mRepository.remove(currentItem)) {
                return when {
                    hasPreviousItem -> {
                        goToPreviousItem()
                    }
                    hasNextItem -> {
                        goToNextItem()
                    }
                    else -> {
                        mCurrentCachedItem = null
                        mCurrentLine = 0
                        ""
                    }
                }
            }
        }
        return currentItemText
    }

    override fun addItem(text : String) : String {
        return if (mRepository.add(FileRepositoryItem(text))) {
            mCurrentCachedItem = null
            mCurrentLine = mRepository.linesCount - 1
            text
        } else {
            currentItemText
        }
    }

    private fun getItemList(lineNumber : Int) =
        mRepository.query(mSpecificationFactory.getFileLinesSpecification(setOf(lineNumber)))
}