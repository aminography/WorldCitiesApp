package com.aminography.worldcities.navigation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * @author aminography
 */
@Parcelize
data class UserListNavArg(
    val city: String
) : Parcelable