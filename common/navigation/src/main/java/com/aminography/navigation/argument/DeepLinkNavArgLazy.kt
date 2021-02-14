package com.aminography.navigation.argument

import android.os.Bundle
import android.os.Parcelable
import androidx.collection.ArrayMap
import kotlin.reflect.KClass

/**
 * @author aminography
 */
class DeepLinkNavArgLazy<T : DeepLinkNavArg>(
    private val navArgClass: KClass<T>,
    private val argumentProducer: () -> Bundle
) : Lazy<T> {

    private var cached: T? = null

    override val value: T
        get() {
            var args = cached
            if (args == null) {
                val arguments = argumentProducer()

                @Suppress("UNCHECKED_CAST")
                val creator: Parcelable.Creator<T>? =
                    (creatorMap[navArgClass] as? Parcelable.Creator<T>) ?: run {
                        var result: Parcelable.Creator<T>? = null
                        for (f in navArgClass.java.fields) {
                            if (f.get(Unit) is Parcelable.Creator<*>) {
                                result = f.get(Unit) as Parcelable.Creator<T>
                                creatorMap[navArgClass] = result
                            }
                        }
                        result
                    }

                args = creator?.let {
                    arguments.getString(KEY_NAV_ARG)?.decodeFromBase64(it)
                        ?: throw IllegalStateException("The received deep-link has no arguments!")
                } ?: throw IllegalStateException(
                    "Class $navArgClass has no CREATOR object!"
                )

                cached = args
            }
            return args
        }

    override fun isInitialized() = cached != null
}

internal val creatorMap = ArrayMap<KClass<out DeepLinkNavArg>, Parcelable.Creator<*>>()