package com.aminography.model.common

/**
 * A generic class that holds a value along with its status.
 *
 * @author aminography
 */
sealed class Result<out R> {

    object Loading : Result<Nothing>() {
        override fun toString(): String = "Loading"
    }

    data class Success<out T>(
        val data: T? = null
    ) : Result<T>()

    data class Error(
        val cause: Throwable? = null
    ) : Result<Nothing>()
}

inline fun <R> Result<R>.onLoading(block: () -> Unit): Result<R> =
    also { if (it is Result.Loading) block() }

inline fun <R> Result<R>.onSuccess(block: (R?) -> Unit): Result<R> =
    also { if (it is Result.Success) block(it.data) }

inline fun <R> Result<R>.onError(block: (Throwable?) -> Unit): Result<R> =
    also { if (it is Result.Error) block(it.cause) }

/**
 * Maps the data part of the [Result] from one type to another.
 *
 * @param transform the transformation operation
 * @return a new instance of [Result] with the transformed data preserving its status
 */
inline fun <T, R> Result<T>.map(crossinline transform: (T) -> R): Result<R> = when (this) {
    is Result.Loading -> Result.Loading
    is Result.Success -> Result.Success(data?.let { transform(it) })
    is Result.Error -> Result.Error(cause)
}
