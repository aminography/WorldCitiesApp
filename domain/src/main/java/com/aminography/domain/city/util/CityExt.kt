package com.aminography.domain.city.util

import com.aminography.model.city.City
import java.util.*

/**
 * @author aminography
 */

val City.key: String
    get() = "${name}, $country".toLowerCase(Locale.getDefault())