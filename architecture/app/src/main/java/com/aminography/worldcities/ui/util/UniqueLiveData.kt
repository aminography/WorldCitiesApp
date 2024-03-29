package com.aminography.worldcities.ui.util

import androidx.annotation.MainThread
import androidx.lifecycle.MutableLiveData

/**
 * An implementation of [androidx.lifecycle.LiveData] that does not trigger the observers for
 * repetitive values.
 *
 * @author aminography
 */
class UniqueLiveData<T> : MutableLiveData<T>() {

    private var previous: T? = null

    @MainThread
    override fun setValue(value: T?) {
        if (value != previous) {
            previous = value
            super.setValue(value)
        }
    }
}