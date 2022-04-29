package com.example.diaryapp.domain

import javax.inject.Inject

class DeleteDiaryItemUseCase @Inject constructor(
    private val diaryListRepository: DiaryListRepository) {

    suspend fun deleteDiaryItem(diaryItem: DiaryItem){

        diaryListRepository.deleteDiaryItem(diaryItem)
    }
}