package com.example.diaryapp.domain

import androidx.lifecycle.LiveData

interface DiaryListRepository {

    suspend fun deleteDiaryItem(diaryItem: DiaryItem)

    suspend fun addDiaryItem(diaryItem: DiaryItem)

    suspend fun editDiaryItem(diaryItem: DiaryItem)

    suspend fun getDiaryItem(diaryItemId: Int): DiaryItem

    fun getDiaryList(): LiveData<List<DiaryItem>>

}