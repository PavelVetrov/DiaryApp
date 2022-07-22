package com.example.diaryapp.presentation.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
class ViewModalFactory @Inject constructor(
    private val viewModalProvider: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory{


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModalProvider[modelClass]?.get() as T
    }
}