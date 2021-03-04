package com.aminography.scope.annotation.foundation

import javax.inject.Scope

/**
 * A scope associated with the lifecycle of persistent-data foundation-level components.
 *
 * @author aminography
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PersistentDataScope