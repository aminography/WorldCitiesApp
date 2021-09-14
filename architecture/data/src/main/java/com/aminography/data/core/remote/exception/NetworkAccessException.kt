package com.aminography.data.core.remote.exception

/**
 * @author aminography
 */
class NetworkAccessException(
    message: String? = null,
    cause: Throwable? = null
) : ApiException(message, cause)
