package com.example.diaryapp.domain

import androidx.lifecycle.LiveData
import javax.inject.Inject

class GetDiaryListUseCase @Inject constructor(
    private val diaryListRepository: DiaryListRepository) {

    fun getDiaryList(): LiveData<List<DiaryItem>>{

        return diaryListRepository.getDiaryList()
    }
}