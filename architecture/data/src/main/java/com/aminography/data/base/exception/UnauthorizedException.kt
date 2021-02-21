package com.aminography.data.base.exception

/**
 * @author aminography
 */
class UnauthorizedException(
    message: String? = null,
    cause: Throwable? = null
) : ApiException(message, cause)