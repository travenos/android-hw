package com.example.db_file.domain.impl

import com.example.db_file.data.FileRepository
import com.example.db_file.domain.DbFileInteractor
import javax.inject.Inject


class DbFileInteractorImpl @Inject constructor(private val mRepository : FileRepository,
                                               private var mCurrentLine : Int = 0) : DbFileInteractor {
    private val mSpecificationFactory = mRepository.specificationFactory

    override val currentItemText : String
        get() {
            val itemList = getItemList(mCurrentLine)
            return if (itemList?.isEmpty() == false) itemList[0].text else ""
        }

    override val currentItemNumber : Int?
        get() = mCurrentLine

    override val hasNextItem : Boolean
        get() = mCurrentLine < mRepository.linesCount - 1

    override val hasPreviousItem : Boolean
        get() = mCurrentLine > 0

    override fun goToNextItem() : String? {
        val itemList = getItemList(mCurrentLine + 1)
        if (itemList?.isEmpty() == false) {
            ++mCurrentLine
            return itemList[0].text
        }
        return null
    }

    override fun goToPreviousItem() : String? {
        val itemList = getItemList(mCurrentLine - 1)
        if (itemList?.isEmpty() == false) {
            --mCurrentLine
            return itemList[0].text
        }
        return null
    }

    private fun getItemList(lineNumber : Int) =
        mRepository.query(mSpecificationFactory.getFileLinesSpecification(setOf(lineNumber)))
}