package com.example.diaryapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.diaryapp.R
import com.example.diaryapp.domain.DiaryItem

class DiaryListAdapter : ListAdapter<DiaryItem, DiaryItemViewHolder>(DiffUtilItem()) {

    var onDiaryItemShotClick: ((DiaryItem) -> Unit)? = null
    var onDiaryItemDeleteClick: ((DiaryItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiaryItemViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.diary_item, parent, false)
        return DiaryItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiaryItemViewHolder, position: Int) {
        val diaryItem = getItem(position)
        holder.tvDate.text = diaryItem.date
        holder.tvTitle.text = diaryItem.title
        holder.tvText.text = diaryItem.text
        holder.tvDelete.setOnClickListener {
            onDiaryItemDeleteClick?.invoke(diaryItem)
        }
        holder.itemView.setOnClickListener {

            onDiaryItemShotClick?.invoke(diaryItem)
        }
    }

}
