package com.example.diaryapp.di

import android.app.Application
import com.example.diaryapp.presentation.FragmentAddDiary
import com.example.diaryapp.presentation.FragmentEditDiary
import com.example.diaryapp.presentation.FragmentMainDiary
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DataModule::class, ViewModalModule::class])
interface ApplicationComponent {

    fun inject(fragment: FragmentEditDiary)

    fun inject(fragment: FragmentAddDiary)

    fun inject(fragment: FragmentMainDiary)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance
            application: Application
        ): ApplicationComponent
    }

}