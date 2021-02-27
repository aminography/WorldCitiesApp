package com.aminography.worldcities.userlist.util

import android.widget.ImageView
import com.aminography.worldcities.userlist.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * @author aminography
 */

var ImageView.imageUrl: String
    get() = ""
    set(value) {
        Glide.with(context)
            .load(value)
            .apply(RequestOptions.placeholderOf(R.drawable.shape_default_placeholder))
            .into(this)
    }