package com.example.diaryapp.domain

import javax.inject.Inject

class AddDiaryItemUseCase @Inject constructor(
    private val diaryListRepository: DiaryListRepository) {

    suspend fun addDiaryList(diaryItem: DiaryItem){

        diaryListRepository.addDiaryItem(diaryItem)

    }
}