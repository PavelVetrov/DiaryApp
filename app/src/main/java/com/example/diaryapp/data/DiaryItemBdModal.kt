package com.example.diaryapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "diary_note")
data class DiaryItemBdModal(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val title: String,
    val date: String,
    val text: String,
    )
