package com.example.diaryapp.di

import android.app.Application
import com.example.diaryapp.data.AppDataBaseDiary
import com.example.diaryapp.data.DiaryListDao
import com.example.diaryapp.data.DiaryListRepositoryImpl
import com.example.diaryapp.domain.DiaryListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindRepository(impl: DiaryListRepositoryImpl): DiaryListRepository


    companion object {

        @ApplicationScope
        @Provides
        fun providesDiaryListDao(application: Application): DiaryListDao {

            return AppDataBaseDiary.getInstance(application).diaryListDao()
        }
    }

}