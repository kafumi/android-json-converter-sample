package io.github.kafumi.jsonconvertersample.data

import com.squareup.moshi.Json

data class ValuePack(
        @field:Json(name = "int") val intValue: Int,
        @field:Json(name = "long") val longValue: Long,
        @field:Json(name = "float") val floatValue: Float,
        @field:Json(name = "double") val doubleValue: Double,
        @field:Json(name = "string") val stringValue: String): Validatable {

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