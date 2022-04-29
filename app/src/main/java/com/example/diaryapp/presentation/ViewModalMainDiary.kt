package com.example.diaryapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.domain.DeleteDiaryItemUseCase
import com.example.diaryapp.domain.DiaryItem
import com.example.diaryapp.domain.GetDiaryListUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModalMainDiary @Inject constructor(
    private val deleteDiaryItemUseCase: DeleteDiaryItemUseCase,
    private val getDiaryListUseCase: GetDiaryListUseCase
) : ViewModel() {


    val diaryList = getDiaryListUseCase.getDiaryList()

    fun deleteItem(diaryItem: DiaryItem) {
        viewModelScope.launch {
            deleteDiaryItemUseCase.deleteDiaryItem(diaryItem)
        }
    }

}