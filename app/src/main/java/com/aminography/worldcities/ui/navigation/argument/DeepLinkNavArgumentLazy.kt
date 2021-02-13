package com.aminography.worldcities.ui.navigation.argument

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.MainThread
import androidx.collection.ArrayMap
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

/**
 * @author aminography
 */
class DeepLinkNavArgumentLazy<T : DeepLinkNavArgument>(
    private val navArgumentClass: KClass<T>,
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
                    (creatorMap[navArgumentClass] as? Parcelable.Creator<T>) ?: run {
                        var result: Parcelable.Creator<T>? = null
                        for (f in navArgumentClass.java.fields) {
                            if (f.get(Unit) is Parcelable.Creator<*>) {
                                result = f.get(Unit) as Parcelable.Creator<T>
                                creatorMap[navArgumentClass] = result
                            }
                        }
                        result
                    }

                args = creator?.let {
                    arguments.getString("arg")?.decodeFromBase64(it)
                } ?: throw IllegalStateException(
                    "DeepLinkNavArgument class $navArgumentClass has no CREATOR object!"
                )

                cached = args
            }
            return args
        }

    override fun isInitialized() = cached != null
}

internal val creatorMap = ArrayMap<KClass<out DeepLinkNavArgument>, Parcelable.Creator<*>>()

@MainThread
inline fun <reified T : DeepLinkNavArgument> Fragment.deepLinkNavArgument() =
    DeepLinkNavArgumentLazy(T::class) {
        arguments ?: throw IllegalStateException("Fragment $this has null arguments")
    }