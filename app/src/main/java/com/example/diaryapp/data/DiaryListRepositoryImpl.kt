package com.example.diaryapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.diaryapp.domain.DiaryItem
import com.example.diaryapp.domain.DiaryListRepository
import javax.inject.Inject

class DiaryListRepositoryImpl @Inject constructor(
    private val diaryListDao: DiaryListDao,
    private val mapper: DiaryListMapper
) : DiaryListRepository {


    override suspend fun deleteDiaryItem(diaryItem: DiaryItem) {
        diaryListDao.deleteDiaryItem(diaryItem.id)
    }

    override suspend fun addDiaryItem(diaryItem: DiaryItem) {
        diaryListDao.addDiaryItem(mapper.mapDiaryEntityToDbModal(diaryItem))
    }

    override suspend fun editDiaryItem(diaryItem: DiaryItem) {
        diaryListDao.addDiaryItem(mapper.mapDiaryEntityToDbModal(diaryItem))
    }

    override suspend fun getDiaryItem(diaryItemId: Int): DiaryItem {
        val dbModal = diaryListDao.getDiaryItem(diaryItemId)
        return mapper.mapDbModalToDiaryItem(dbModal)
    }

    override fun getDiaryList(): LiveData<List<DiaryItem>> =
        Transformations.map(diaryListDao.getDiaryList()) {
            mapper.mapListDbModalToEntity(it)
        }


}