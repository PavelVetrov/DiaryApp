package com.example.diaryapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.diaryapp.domain.DiaryItem
import com.example.diaryapp.domain.EditDiaryItemUseCase
import com.example.diaryapp.domain.GetDiaryItemUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModalEditDiary @Inject constructor(
    private val editDiaryItemUseCase: EditDiaryItemUseCase,
    private val getDiaryItemUseCase: GetDiaryItemUseCase
) : ViewModel() {


    private val _diaryItem = MutableLiveData<DiaryItem>()
    val diaryItem: LiveData<DiaryItem> = _diaryItem

    fun getDiaryItem(diaryItem: Int) {
        viewModelScope.launch {
            val item = getDiaryItemUseCase.getDiaryItem(diaryItem)
            _diaryItem.value = item
        }
    }

    fun editDiaryItem(text: String, title: String, date: String) {

        viewModelScope.launch {
            _diaryItem.value?.let {
                val item = it.copy(title = title, date = date, text = text)
                editDiaryItemUseCase.editDiaryItem(item)
            }
        }
    }
}