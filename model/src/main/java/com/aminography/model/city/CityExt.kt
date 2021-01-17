package com.aminography.model.city

import java.util.*

/**
 * @author aminography
 */

val City.key: String
    get() = "${name}, $country".toLowerCase(Locale.getDefault())