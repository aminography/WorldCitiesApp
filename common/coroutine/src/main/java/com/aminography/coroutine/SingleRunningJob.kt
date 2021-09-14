package com.aminography.coroutine

import com.aminography.coroutine.util.cancelIfActive
import kotlinx.coroutines.Job
import kotlin.reflect.KProperty

/**
 * This class helps to run coroutines in a unique manner by cancelling the previous one using a
 * delegated property.
 *
 * To enjoy this functionality, just define a [Job] using delegation, like this:
 * ```
 * val myJob: Job by UniqueJob()
 * ```
 *
 * Then assign the returned job of a coroutine builder to it:
 * ```
 * myJob = SomeScope.launch { /* do whatever you want to run uniquely */ }
 * ```
 */
class SingleRunningJob {

    private var internalJob: Job = Job()

    operator fun getValue(thisRef: Any?, property: KProperty<*>): Job {
        return internalJob
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Job) {
        internalJob.cancelIfActive()
        internalJob = value
    }
}


