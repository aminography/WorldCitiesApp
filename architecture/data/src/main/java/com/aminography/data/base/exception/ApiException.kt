package com.aminography.data.base.exception

/**
 * @author aminography
 */
abstract class ApiException(
    override val message: String?,
    override val cause: Throwable?
) : Exception(message, cause)