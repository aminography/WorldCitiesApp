package com.aminography.data.remote.base.exception

/**
 * @author aminography
 */
class UnauthorizedException(
    message: String? = null,
    cause: Throwable? = null
) : ApiException(message, cause)