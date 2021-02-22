package com.aminography.data.core.remote

import com.aminography.data.core.remote.exception.ServerInternalException
import com.aminography.data.core.remote.exception.ServerUnreachableException
import com.aminography.data.core.remote.exception.UnauthorizedException
import com.aminography.data.core.remote.exception.UnknownApiException
import com.aminography.domain.base.Result
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection.*

/**
 * @author aminography
 */
abstract class BaseRemoteDataSource {

    internal suspend fun <T> wrapResponse(function: suspend () -> Response<T>): Result<T> {
        return try {
            function.invoke().let {
                when (it.code()) {
                    HTTP_OK -> it.body().let { b -> Result.Success(b) }
                    HTTP_UNAUTHORIZED, HTTP_FORBIDDEN -> Result.Error(UnauthorizedException(it.message()))
                    HTTP_INTERNAL_ERROR -> Result.Error(ServerInternalException(it.message()))
                    HTTP_BAD_GATEWAY, HTTP_UNAVAILABLE -> Result.Error(ServerInternalException(it.message()))
                    else -> {
                        @Suppress("BlockingMethodInNonBlockingContext")
                        val msg = it.errorBody()?.string()
                        val errorMsg = if (msg.isNullOrEmpty()) it.message() else msg
                        Result.Error(UnknownApiException(errorMsg ?: "unknown error"))
                    }
                }
            }
        } catch (e: IOException) {
            Result.Error(ServerUnreachableException(e.message))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
