package com.aminography.data.base.exception

/**
 * @author aminography
 */
class ServerInternalException(
    message: String? = null,
    cause: Throwable? = null
) : ApiException(message, cause)