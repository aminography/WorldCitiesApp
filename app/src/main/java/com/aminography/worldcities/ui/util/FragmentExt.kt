package com.aminography.worldcities.ui.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aminography.worldcities.MainApplication

/**
 * An extension function on [Fragment] objects to produce and instance of [ViewModel] using a
 * [ViewModelProvider.Factory].
 *
 * @param VM the type of the desired [ViewModel] class.
 * @param factory the [ViewModelProvider.Factory] of the desired [ViewModel] class.
 *
 * @return an instance of desired [ViewModel] class.
 *
 * @author aminography
 */
inline fun <reified VM : ViewModel> Fragment.createViewModel(
    factory: ViewModelProvider.Factory
): VM = ViewModelProvider(this, factory)[VM::class.java]

/**
 * An extension property on [Fragment] objects to make access to [MainApplication] instance easier.
 *
 * @return the instance of [MainApplication].
 *
 * @author aminography
 */
inline val Fragment.application: MainApplication?
    get() = (context?.applicationContext as? MainApplication)