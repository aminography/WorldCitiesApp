package com.aminography.data.remote.base

import com.aminography.data.remote.base.exception.ServerInternalException
import com.aminography.data.remote.base.exception.ServerUnreachableException
import com.aminography.data.remote.base.exception.UnauthorizedException
import com.aminography.data.remote.base.exception.UnknownApiException
import com.aminography.domain.base.Result
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection.*

/**
 * @author aminography
 */
abstract class BaseRemoteDataSource {

    @Suppress("BlockingMethodInNonBlockingContext")
    internal suspend fun <T> wrapResponse(function: suspend () -> Response<T>): Result<T> {
        return try {
            function.invoke().let {
                when (it.code()) {
                    HTTP_OK -> it.body().let { b -> Result.Success(b) }
                    HTTP_UNAUTHORIZED -> Result.Error(UnauthorizedException(it.message()))
                    HTTP_INTERNAL_ERROR -> Result.Error(ServerInternalException(it.message()))
                    HTTP_BAD_GATEWAY -> Result.Error(ServerInternalException(it.message()))
                    else -> {
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