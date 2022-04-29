package com.example.diaryapp.domain

import javax.inject.Inject

class EditDiaryItemUseCase @Inject constructor(
    private val diaryListRepository: DiaryListRepository) {

    suspend fun editDiaryItem(diaryItem: DiaryItem){

        diaryListRepository.editDiaryItem(diaryItem)
    }
}