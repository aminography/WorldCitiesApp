package com.aminography.worldcities.ui.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * @author aminography
 */

fun <T> Flow<T>.collect(owner: LifecycleOwner, collector: (T) -> Unit): Job =
    owner.run {
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                collect { collector(it) }
            }
        }
    }
