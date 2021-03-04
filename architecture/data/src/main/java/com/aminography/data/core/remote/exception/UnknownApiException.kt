package com.aminography.data.core.remote.exception

/**
 * @author aminography
 */
class UnknownApiException(
    message: String? = null,
    cause: Throwable? = null
) : ApiException(message, cause)