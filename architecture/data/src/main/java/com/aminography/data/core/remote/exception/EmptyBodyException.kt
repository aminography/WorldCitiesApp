package com.aminography.data.core.remote.exception

/**
 * @author aminography
 */
class EmptyBodyException(
    message: String? = null,
    cause: Throwable? = null
) : ApiException(message, cause)
