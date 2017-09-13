package io.github.kafumi.jsonconvertersample.data

import com.squareup.moshi.Json

data class ValuePack(
        @Json(name = "int") val intValue: Int,
        @Json(name = "long") val longValue: Long,
        @Json(name = "float") val floatValue: Float,
        @Json(name = "double") val doubleValue: Double,
        @Json(name = "string") val stringValue: String): Validatable {

    constructor() : this(
            intValue = Int.MIN_VALUE,
            longValue = Long.MIN_VALUE,
            floatValue = Float.NaN,
            doubleValue = Double.NaN,
            stringValue = "")

    override val isValid: Boolean
        get() = intValue != Int.MIN_VALUE &&
                longValue != Long.MIN_VALUE &&
                !floatValue.isNaN() &&
                !doubleValue.isNaN() &&
                !stringValue.isNullOrEmpty()
}