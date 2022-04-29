package com.example.diaryapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.diaryapp.domain.DiaryItem

class DiffUtilItem : DiffUtil.ItemCallback<DiaryItem>() {

    override fun areItemsTheSame(oldItem: DiaryItem, newItem: DiaryItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DiaryItem, newItem: DiaryItem): Boolean {
        return oldItem == newItem
    }
}