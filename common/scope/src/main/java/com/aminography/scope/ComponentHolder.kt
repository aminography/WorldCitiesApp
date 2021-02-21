package com.aminography.scope

/**
 * @author aminography
 */
abstract class ComponentHolder<T> {

    @Volatile
    private var component: T? = null

    protected abstract fun createComponent(): T

    fun get(): T = when {
        component != null -> component!!
        else -> synchronized(this) {
            if (component == null) component = createComponent()
            component!!
        }
    }

    fun clear() {
        component = null
    }
}