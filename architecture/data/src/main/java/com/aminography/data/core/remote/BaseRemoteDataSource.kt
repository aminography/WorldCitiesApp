package com.aminography.data.core.remote

import com.aminography.data.core.remote.exception.EmptyBodyException
import com.aminography.data.core.remote.exception.NetworkAccessException
import com.aminography.data.core.remote.exception.ServerInternalException
import com.aminography.data.core.remote.exception.UnauthorizedException
import com.aminography.data.core.remote.exception.UnknownApiException
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection.HTTP_BAD_GATEWAY
import java.net.HttpURLConnection.HTTP_INTERNAL_ERROR
import java.net.HttpURLConnection.HTTP_NO_CONTENT
import java.net.HttpURLConnection.HTTP_UNAUTHORIZED

/**
 * @author aminography
 */
abstract class BaseRemoteDataSource {

    internal suspend fun <T> wrapResponse(function: suspend () -> Response<T>): Result<T> {
        return try {
            function.invoke().let {
                if (it.isSuccessful) {
                    it.body()
                        ?.let { body -> Result.success(body) }
                        ?: Result.failure(EmptyBodyException())
                } else Result.failure(
                    when (it.code()) {
                        HTTP_NO_CONTENT -> EmptyBodyException()
                        HTTP_UNAUTHORIZED -> UnauthorizedException(it.message())
                        HTTP_INTERNAL_ERROR -> ServerInternalException(it.message())
                        HTTP_BAD_GATEWAY -> ServerInternalException(it.message())
                        else -> {
                            @Suppress("BlockingMethodInNonBlockingContext")
                            val msg = it.errorBody()?.string()
                            val errorMsg = if (msg.isNullOrEmpty()) it.message() else msg
                            UnknownApiException(errorMsg ?: "Unknown Error!")
                        }
                    }
                )
            }
        } catch (e: IOException) {
            Result.failure(NetworkAccessException(e.message ?: ""))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
