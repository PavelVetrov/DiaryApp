package com.example.diaryapp.di

import androidx.lifecycle.ViewModel
import com.example.diaryapp.presentation.viewmodal.ViewModalAddDiary
import com.example.diaryapp.presentation.viewmodal.ViewModalEditDiary
import com.example.diaryapp.presentation.viewmodal.ViewModalMainDiary
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModalModule {

    @IntoMap
    @Binds
    @ViewModalKey(ViewModalAddDiary::class)
    fun bindViewModalAddDiary(viewModel: ViewModalAddDiary): ViewModel

    @IntoMap
    @Binds
    @ViewModalKey(ViewModalEditDiary::class)
    fun bindViewModalEditDiary(viewModel: ViewModalEditDiary): ViewModel

    @IntoMap
    @Binds
    @ViewModalKey(ViewModalMainDiary::class)
    fun bindViewModalMainDiary(viewModel: ViewModalMainDiary): ViewModel
}