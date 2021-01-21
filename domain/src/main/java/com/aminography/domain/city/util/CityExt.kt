package com.aminography.domain.city.util

import com.aminography.model.city.City
import java.util.*

/**
 * An extension property on [City] entity to define a key for searching intention based on the name
 * and country code of the [City].
 *
 * @author aminography
 */
val City.key: String
    get() = "${name}, $country".toLowerCase(Locale.getDefault())