package com.aminography.domain.util

import com.aminography.model.common.Result
import com.aminography.model.common.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * @author aminography
 */

inline fun <T, R> Result<List<T>>.mapList(crossinline transform: (T) -> R): Result<List<R>> =
    map { it.map(transform) }

inline fun <T, R> Flow<Result<T>>.mapResult(crossinline transform: (T) -> R): Flow<Result<R>> =
    map { it.map(transform) }

inline fun <T, R> Flow<Result<List<T>>>.mapListInResult(crossinline transform: (T) -> R): Flow<Result<List<R>>> =
    mapResult { it.map(transform) }
