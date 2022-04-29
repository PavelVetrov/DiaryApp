package com.example.diaryapp.domain

import javax.inject.Inject

class GetDiaryItemUseCase @Inject constructor(
    private val diaryListRepository: DiaryListRepository) {

    suspend fun getDiaryItem(diaryItemId: Int):DiaryItem{

        return diaryListRepository.getDiaryItem(diaryItemId)
    }
}