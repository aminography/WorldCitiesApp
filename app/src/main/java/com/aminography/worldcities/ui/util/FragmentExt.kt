package com.aminography.worldcities.ui.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aminography.worldcities.MainApplication

/**
 * @author aminography
 */

inline fun <reified VM : ViewModel> Fragment.createViewModel(
    factory: ViewModelProvider.Factory
): VM = ViewModelProvider(this, factory)[VM::class.java]

inline val Fragment.application: MainApplication?
    get() = (context?.applicationContext as? MainApplication)