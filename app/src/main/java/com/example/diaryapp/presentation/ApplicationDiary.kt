package com.example.diaryapp.presentation

import android.app.Application
import com.example.diaryapp.di.DaggerApplicationComponent

class ApplicationDiary : Application() {

    val component by lazy {

        DaggerApplicationComponent.factory().create(this)
    }
}