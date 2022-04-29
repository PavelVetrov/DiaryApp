package com.example.diaryapp.data

import com.example.diaryapp.domain.DiaryItem
import javax.inject.Inject


class DiaryListMapper @Inject constructor() {

    fun mapDiaryEntityToDbModal(diaryItem: DiaryItem): DiaryItemBdModal {

        return DiaryItemBdModal(
            id = diaryItem.id,
            title = diaryItem.title,
            date = diaryItem.date,
            text = diaryItem.text
        )
    }

    fun mapDbModalToDiaryItem(diaryItemBdModal: DiaryItemBdModal): DiaryItem {

        return DiaryItem(
            id = diaryItemBdModal.id,
            title = diaryItemBdModal.title,
            date = diaryItemBdModal.date,
            text = diaryItemBdModal.text,
        )
    }

    fun mapListDbModalToEntity(list: List<DiaryItemBdModal>) = list.map {

        mapDbModalToDiaryItem(it)
    }
}