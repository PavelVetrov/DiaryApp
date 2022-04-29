package com.example.diaryapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface DiaryListDao {

    @Query("SELECT*FROM diary_note")
    fun getDiaryList(): LiveData<List<DiaryItemBdModal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDiaryItem(diaryItemBdModal: DiaryItemBdModal)

    @Query("DELETE FROM diary_note WHERE id=:diaryItemId")
    suspend fun deleteDiaryItem(diaryItemId: Int)

    @Query("SELECT*FROM  diary_note WHERE id=:diaryItemId LIMIT 1")
    suspend fun getDiaryItem(diaryItemId: Int): DiaryItemBdModal

}