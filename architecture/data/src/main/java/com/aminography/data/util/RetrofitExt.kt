package com.aminography.data.util

import retrofit2.Retrofit

/**
 * @author aminography
 */

internal inline fun <reified T> Retrofit.create(): T = create(T::class.java)
