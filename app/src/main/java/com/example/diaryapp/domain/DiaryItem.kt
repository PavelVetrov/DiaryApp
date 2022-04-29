package com.example.diaryapp.domain

data class DiaryItem(
    val title: String,
    val date: String,
    val text: String,
    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}


