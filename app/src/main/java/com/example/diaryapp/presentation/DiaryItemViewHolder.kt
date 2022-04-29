package com.example.diaryapp.presentation

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.diaryapp.R

class DiaryItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tvDate = view.findViewById<TextView>(R.id.rv_date)
    val tvTitle = view.findViewById<TextView>(R.id.rv_title)
    val tvText = view.findViewById<TextView>(R.id.rv_text)
    val tvDelete = view.findViewById<ImageView>(R.id.delete_image_button)

}