package com.example.diaryapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.domain.AddDiaryItemUseCase
import com.example.diaryapp.domain.DiaryItem
import kotlinx.coroutines.launch
import javax.inject.Inject


class ViewModalAddDiary @Inject constructor(
    private val addDiaryItemUseCase: AddDiaryItemUseCase) : ViewModel() {

    fun addDiaryItem(diaryItem: DiaryItem) {
        viewModelScope.launch{
            addDiaryItemUseCase.addDiaryList(diaryItem)
        }
    }
}