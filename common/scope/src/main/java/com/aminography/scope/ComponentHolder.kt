package com.aminography.scope

/**
 * @author aminography
 */
abstract class ComponentHolder<T> {

    @Volatile
    private var component: T? = null

    protected abstract fun createComponent(): T

    fun get(): T = component ?: synchronized(this) {
        component ?: createComponent().also { component = it }
    }

    fun clear() {
        component = null
    }
}
