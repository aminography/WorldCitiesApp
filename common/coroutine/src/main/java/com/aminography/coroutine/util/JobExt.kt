package com.aminography.coroutine.util

import kotlinx.coroutines.Job

/**
 * @author aminography
 */

fun Job?.cancelIfActive() {
    this?.run { if (isActive) cancel() }
}
