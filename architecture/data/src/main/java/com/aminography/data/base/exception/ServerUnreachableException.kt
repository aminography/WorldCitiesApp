package com.aminography.data.base.exception

/**
 * @author aminography
 */
class ServerUnreachableException(
    message: String? = null,
    cause: Throwable? = null
) : ApiException(message, cause)