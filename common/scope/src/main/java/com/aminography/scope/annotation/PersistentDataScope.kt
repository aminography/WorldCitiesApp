package com.aminography.scope.annotation

import javax.inject.Scope

/**
 * A scope associated with the lifecycle of persistent data components.
 *
 * @author aminography
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PersistentDataScope