package com.aminography.domain.base

/**
 * A generic class that holds a value along with its status.
 *
 * @param R the generic type of the data that holds by objects of this class.
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

/**
 * Executes the input block if the [Result] object is [Result.Loading].
 *
 * @param block the block of code to be executed.
 */
inline fun <R> Result<R>.onLoading(block: () -> Unit): Result<R> =
    also { if (it is Result.Loading) block() }

/**
 * Executes the input block if the [Result] object is instance of [Result.Success].
 *
 * @param block the block of code to be executed.
 */
inline fun <R> Result<R>.onSuccess(block: (R?) -> Unit): Result<R> =
    also { if (it is Result.Success) block(it.data) }

/**
 * Executes the input block if the [Result] object is instance of [Result.Error].
 *
 * @param block the block of code to be executed.
 */
inline fun <R> Result<R>.onError(block: (Throwable?) -> Unit): Result<R> =
    also { if (it is Result.Error) block(it.cause) }
