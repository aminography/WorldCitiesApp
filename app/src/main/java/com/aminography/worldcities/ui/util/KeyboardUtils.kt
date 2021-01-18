package com.aminography.worldcities.ui.util

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

/**
 * @author aminography
 */

fun EditText.hideKeyboard(): Boolean {
    return (context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(windowToken, 0)
}
