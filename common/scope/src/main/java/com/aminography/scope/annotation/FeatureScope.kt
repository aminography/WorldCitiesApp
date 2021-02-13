package com.aminography.scope.annotation

import javax.inject.Scope

/**
 * A scope associated with the lifecycle of feature-level components.
 *
 * @author aminography
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FeatureScope