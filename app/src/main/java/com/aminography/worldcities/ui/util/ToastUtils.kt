@file:Suppress("unused")

package com.aminography.worldcities.ui.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

private var toast: Toast? = null

/**
 * An extension function on [Context] objects to show a toast message and cancel previous one if any.
 *
 * @param message the message to be shown.
 *
 * @author aminography
 */
fun Context.toast(message: CharSequence?) {
    toast?.cancel()
    toast = message?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT) }?.apply { show() }
}

/**
 * An extension function on [Context] objects to show a long toast message and cancel previous one
 * if any.
 *
 * @param message the message to be shown.
 *
 * @author aminography
 */
fun Context.longToast(message: CharSequence?) {
    toast?.cancel()
    toast = message?.let { Toast.makeText(this, it, Toast.LENGTH_LONG) }?.apply { show() }
}

/**
 * An extension function on [Context] objects to show a toast message and cancel previous one if any.
 *
 * @param message the resource id of the message to be shown.
 *
 * @author aminography
 */
fun Context.toast(@StringRes message: Int) {
    toast?.cancel()
    toast = Toast.makeText(this, message, Toast.LENGTH_SHORT).apply { show() }
}

/**
 * An extension function on [Context] objects to show a long toast message and cancel previous one
 * if any.
 *
 * @param message the resource id of the message to be shown.
 *
 * @author aminography
 */
fun Context.longToast(@StringRes message: Int) {
    toast?.cancel()
    toast = Toast.makeText(this, message, Toast.LENGTH_LONG).apply { show() }
}